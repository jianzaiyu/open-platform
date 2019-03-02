package cn.ce.framework.hbase.config;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

/**
 *  HBaseConnection Factory
 *  be use for create HBaseConnect
 *  implements  PooledObjectFactory
 *
 *  @author liubin
 */
public class HBaseConnectionFactory implements PooledObjectFactory<Connection> {

    private final Configuration conf ;
    public HBaseConnectionFactory(Configuration conf){
        this.conf = conf;
    }

    public PooledObject<Connection> makeObject() throws Exception {
        return new DefaultPooledObject<Connection>(ConnectionFactory.createConnection(conf));
    }

    public void destroyObject(PooledObject<Connection> pooledObject) throws Exception {
        Connection conn = pooledObject.getObject();
        if(conn != null){
            conn.close();
        }
    }

    public boolean validateObject(PooledObject<Connection> pooledObject) {
        Connection conn = pooledObject.getObject();
        return conn != null && !conn.isAborted() && !conn.isClosed() ;
    }

    public void activateObject(PooledObject<Connection> pooledObject) throws Exception {

    }

    public void passivateObject(PooledObject<Connection> pooledObject) throws Exception {

    }
}
