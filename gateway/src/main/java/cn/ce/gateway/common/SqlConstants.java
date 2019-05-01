package cn.ce.gateway.common;

public class SqlConstants {
    public static final String extractAllRouteSql =
            "SELECT" +
                    "listen_path path," +
                    "GROUP_CONCAT(CONCAT(" +
                    "IFNULL(" +
                    "CONCAT(default_target_url, ',')," +
                    "''" +
                    ")," +
                    "resource_type," +
                    "','," +
                    "version" +
                    ") SEPARATOR ';') url" +
                    "FROM" +
                    "api_detail" +
                    "GROUP BY path";
    public static final String clientTargetUrlSql =
            "SELECT" +
                    "CONCAT(client_id,IFNULL(saas.resource_type,'')) cr_key," +
                    "CONCAT(IFNULL(target_url,''),'?tenantId=',product_instance_id) final_url" +
                    "FROM" +
                    "diy_apply" +
                    "LEFT JOIN saas ON product_instance_id = saas_id";
}
