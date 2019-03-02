package cn.ce.framework.hbase.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Connection;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * HBase connection pool the main class
 *
 * @author : liubin1@300.cn
 */
@Slf4j
public class HBaseConnectPool {

    protected GenericObjectPool<Connection> internalPool;

    private volatile boolean hasInit = false;

    public static HBaseConnectPool getInstance() {
        return InstanceHolder.builder;
    }

    private static class InstanceHolder {
        private static HBaseConnectPool builder = new HBaseConnectPool();
    }

    private HBaseConnectPool() {
    }


    public void init(HBaseProperty hBaseProperty) {
        if (hasInit) {

        }
        GenericObjectPoolConfig defaultPoolConfig = new GenericObjectPoolConfig();
        defaultPoolConfig.setMaxIdle(hBaseProperty.getMaxIdel());
        defaultPoolConfig.setMinIdle(hBaseProperty.getMinIdle());
        defaultPoolConfig.setMaxTotal(hBaseProperty.getMaxTotal());
        defaultPoolConfig.setMinEvictableIdleTimeMillis(hBaseProperty.getMinEvictIdleTimeMillis());

//        String confFiles = hBaseProperty.getHadoopConf();
//        String[] files = confFiles.split(",");

        Configuration conf = new Configuration();
//        for (String f : files) {
//            String classpath = this.getClass().getResource("/").getPath();
//            conf.addResource(
//                    new Path(classpath + hBaseProperty.getProfile() + File.separator + f));
//        }
        if(!StringUtils.hasLength(hBaseProperty.getZookeeperQuorum())){
            log.error("Please config property 'hbase.zookeeper.quorum'");
        }
        conf.set("hbase.cluster.distributed", hBaseProperty.getClusterDistributed());
        conf.set("hbase.zookeeper.quorum", hBaseProperty.getZookeeperQuorum());
        conf.set("zookeeper.znode.parent", hBaseProperty.getZookeeperZnodeParent());
        conf.set("hbase.zookeeper.useMulti", hBaseProperty.getZookeeperUseMulti());
        conf.set("zookeeper.session.timeout", hBaseProperty.getZookeeperSessionTimeout());
        if (this.internalPool != null) {
            try {
                closeInternalPool();
            } catch (Exception e) {
            }
        }

        this.internalPool = new GenericObjectPool<Connection>(new HBaseConnectionFactory(conf),
                defaultPoolConfig);

        hasInit = true;
    }


    public Connection getResource() {
        try {
            return internalPool.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException("Could not get a resource from the pool", e);
        }
    }

    public void returnResourceObject(final Connection resource) {
        if (resource == null) {
            return;
        }
        try {
            internalPool.returnObject(resource);
        } catch (Exception e) {
            throw new RuntimeException("Could not return the resource to the pool", e);
        }
    }

    public void returnBrokenResource(final Connection resource) {
        if (resource != null) {
            returnBrokenResourceObject(resource);
        }
    }

    public void returnResource(final Connection resource) {
        if (resource != null) {
            returnResourceObject(resource);
        }
    }

    public void destroy() {
        closeInternalPool();
    }

    protected void returnBrokenResourceObject(final Connection resource) {
        try {
            internalPool.invalidateObject(resource);
        } catch (Exception e) {
            throw new RuntimeException("Could not return the resource to the pool", e);
        }
    }

    private void closeInternalPool() {
        try {
            internalPool.close();
        } catch (Exception e) {
            throw new RuntimeException("Could not destroy the pool", e);
        }
    }

    public void execute(@SuppressWarnings("rawtypes") HBaseTask task) {
        Connection conn = null;
        try {
            conn = getResource();
            task.run(conn);
        } catch (Exception e) {
            returnBrokenResource(conn);
            conn = null;
        } finally {
            if (conn != null) {
                returnResource(conn);
            }
        }
    }

    public static abstract class HBaseTask<T> {
        public abstract void run(Connection conn);

        public T getResult() {
            return null;
        }
    }
}
