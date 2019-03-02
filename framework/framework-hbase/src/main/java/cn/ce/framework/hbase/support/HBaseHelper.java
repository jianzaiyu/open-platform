package cn.ce.framework.hbase.support;

import cn.ce.framework.hbase.config.HBaseConnectPool;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author: ggs
 * @date: 2018-12-24 16:41
 **/
@Slf4j
public class HBaseHelper {
    private HBaseConnectPool hBaseConnectPool;

    public HBaseHelper(HBaseConnectPool hBaseConnectPool) {
        this.hBaseConnectPool = hBaseConnectPool;
    }

    public JSONArray scanPageByRowKey(String tableName, String start, String end, String... param) {
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(start));
        scan.setStopRow(Bytes.toBytes(end));
        return scanTemplate(scan, tableName, param);
    }

    public JSONArray scanByRowKey(String tableName, String rowKey, String... param) {
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(rowKey));
        scan.setStopRow(Bytes.toBytes(rowKey));
        return scanTemplate(scan, tableName, param);
    }

    public JSONArray scanAllPageByRowKey(String tableName, String... param) {
        Scan scan = new Scan();
        return scanTemplate(scan, tableName, param);
    }

    public JSONArray scanByRowKeyRegex(String tableName, String rowKey, String... param) {
        Scan scan = new Scan();
        Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(".*?" + rowKey + ".*?"));
        scan.setFilter(filter);
        return scanTemplate(scan, tableName, param);
    }


    public JSONArray scanByRowKeyPrefix(String tableName, String rowKey, String... param) {
        Scan scan = new Scan();
        Filter filter = new PrefixFilter(Bytes.toBytes(rowKey));
        scan.setFilter(filter);
        return scanTemplate(scan, tableName, param);
    }

    public int putMapKeyColumnValue(String tableName, String rowKey, String columnFamily, JSONObject jsonObject) {
        Put put = new Put(Bytes.toBytes(rowKey));
        for (String column : jsonObject.keySet()) {
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(jsonObject.getString(column)));
        }
        return putTemplate(tableName, put);
    }

    public int putJsonStr(String tableName, String rowKey, String columnFamily, String column, String jsonStr) {
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(jsonStr));
        return putTemplate(tableName, put);
    }

    private int putTemplate(String tableName, Put put) {
        int result = 1;
        Table table = null;
        Connection connection = null;
        try {
            long start = System.currentTimeMillis();
            connection = hBaseConnectPool.getResource();
            long end = System.currentTimeMillis();
            log.info("hbase获取连接结束,用时：" + (end - start));
            long start1 = System.currentTimeMillis();
            table = connection.getTable(TableName.valueOf(tableName));
            long end1 = System.currentTimeMillis();
            log.info("hbase获取TABLE结束,用时：" + (end1 - start1) + ",表名:" + tableName);
            long start2 = System.currentTimeMillis();
            table.put(put);
            long end2 = System.currentTimeMillis();
            log.info("hbase put数据结束,用时：" + (end2 - start2));
        } catch (Exception e) {
            result = 0;
            log.error(" scanByRowKey error :" + e.getMessage(), e);
        } finally {
            if (table != null) {
                try {
                    table.close();
                } catch (IOException ex) {
                    log.error(" table close error :" + ex.getMessage(), ex);
                }
            }
            if (connection != null) {
                hBaseConnectPool.returnResource(connection);
            }
        }
        return result;
    }

    private JSONArray scanTemplate(Scan scan, String tableName, String... param) {
        scan.setCaching(1000); //配置缓存 1000 条数据
        Table table = null;
        ResultScanner scanner = null;
        Connection connection = null;
        JSONArray jsonArray = new JSONArray();
        if (param != null && param.length > 1) {
            scan.addColumn(Bytes.toBytes(param[0]), Bytes.toBytes(param[1]));
        } else if (param != null && param.length > 0) {
            scan.addFamily(Bytes.toBytes(param[0]));
        }
        try {
            long start = System.currentTimeMillis();
            connection = hBaseConnectPool.getResource();
            long end = System.currentTimeMillis();
            log.info("hbase获取连接结束,用时：" + (end - start));
            long start1 = System.currentTimeMillis();
            table = connection.getTable(TableName.valueOf(tableName));
            long end1 = System.currentTimeMillis();
            log.info("hbase获取TABLE结束,用时：" + (end1 - start1) + ",表名:" + tableName);
            long start2 = System.currentTimeMillis();
            scanner = table.getScanner(scan);
            for (Result r = scanner.next(); r != null; r = scanner.next()) {
                JSONObject jsonObject = new JSONObject();
                for (Cell cell : r.rawCells()) {
                    jsonObject.put(new String(cell.getQualifier()), new String(cell.getValue()));
                }
                jsonArray.add(jsonObject);
            }
            long end2 = System.currentTimeMillis();
            log.info("hbase获取scanner并循环赋值结束,用时：" + (end2 - start2));
        } catch (Exception e) {
            log.error(" scanByRowKey error :" + e.getMessage(), e);
        } finally {
            if (scanner != null) {
                try {
                    scanner.close();
                } catch (Exception ex) {
                    log.error(" scanner close error :" + ex.getMessage(), ex);
                }
            }
            if (table != null) {
                try {
                    table.close();
                } catch (IOException ex) {
                    log.error(" table close error :" + ex.getMessage(), ex);
                }
            }
            if (connection != null) {
                hBaseConnectPool.returnResource(connection);
            }
        }
        return jsonArray;
    }
}
