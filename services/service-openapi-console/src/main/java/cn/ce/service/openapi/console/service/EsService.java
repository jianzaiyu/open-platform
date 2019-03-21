package cn.ce.service.openapi.console.service;

//import cn.ce.es.util.ElasticsearchUtils;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.RangeQueryBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: EsService
 * @Description: TODO
 * @create 2019/1/8 14:28/MKW
 * @update 2019/1/8 14:28/MKW/(说明。)....多次修改添加多个update
 */
@Service
public class EsService {

//    @Autowired
//    private ElasticsearchUtils utils;
//
//    //查询api调用总量
//    public long getTotalAccessApi(){
//        long startTime = 946656000000L; //2000年
//        long endTime = 253370736000000L; //9999年
//
//        //很有可能发生异常，做好处理
//        try{
//            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("@timestamp").gt(startTime).lt(endTime);
//            SearchResponse response = utils.getclient().prepareSearch("tyk_analytics")
//                    .setTypes("tyk_analytics")
//                    .setQuery(rangeQueryBuilder)
//                    .setSize(0)
//                    .get();
//            return response.getHits().getTotalHits();
//        }catch (Exception e){
//            return -1; //未查询到结果。或者es连接异常
//        }
//    }
}
