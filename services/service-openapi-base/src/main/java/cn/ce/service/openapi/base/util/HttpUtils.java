package cn.ce.service.openapi.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ce.service.openapi.base.common.IOUtils;
import cn.ce.service.openapi.base.common.Result;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2017年8月17日
*/
public class HttpUtils {

	
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	/**
	 * 
	 * @Description : 返回string结果以及状态码，状态码放在Status中
	 * @Author : makangwei
	 * @Date : 2017年8月25日
	 */
	public static Result<String> postJsonWithStateCode(String url,String params,Map<String,String> headers){
		
		logger.info("--------------------post请求地址："+url);
		logger.info("--------------------post请求json体参数："+params);
		Result<String> result = new Result<String>();
		HttpClient client;
		
		try {
			client = getHttpClient();
		} catch (Exception e) {
			logger.info("---------创建httpClient异常----------------");			
			e.printStackTrace();
			return null;
		}
		
		HttpPost post = new HttpPost(url);
		//添加头信息
		for (String key : headers.keySet()) {
			post.addHeader(key, headers.get(key));
		}
		
		HttpEntity entity;
		HttpResponse response;
		try {
			entity = new StringEntity(params);
			post.setEntity(entity);
			response = client.execute(post);
			InputStream is = response.getEntity().getContent();
			int stateCode = response.getStatusLine().getStatusCode();
			String str = IOUtils.convertStreamToString(is);
			logger.info("------------调用返回状态是："+stateCode+"-------------------------");
			logger.info("------------调用返回结果是："+str+"-------------------------");
			result.setData(new org.json.JSONObject(str).toString());
			result.setStatus(stateCode+"");
			return result;
			
		} catch (Exception e) {
			logger.info("---------post请求发生异常----------------");
			e.printStackTrace();
			return null;
		}
	}
	
	public static String postJsonNew(String url,List<NameValuePair> params,Map<String,String> headers) throws ClientProtocolException, IOException{
		
//		params.add(new BasicNameValuePair("content", "abc"));
//		params.add(new BasicNameValuePair("phone", "18246096962"));
//		params.add(new BasicNameValuePair("websiteid", websiteid));
//		params.add(new BasicNameValuePair("sign", "中企动力销售管理部"));
//		params.add(new BasicNameValuePair("providerId", "1"));
//		HttpPost httpPost = new HttpPost("");
//		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//		HttpResponse response = client.execute(httpPost);
		
	    String backMes = null;  
	    HttpPost post = new HttpPost(url);
	    try {  
	    	CloseableHttpClient client = HttpClients.createDefault();
			//添加头信息
			for (String key : headers.keySet()) {
				post.addHeader(key, headers.get(key));
			}
			UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(params,"UTF-8");  
	        post.setEntity( reqEntity);  
	        HttpResponse httpResponse = client.execute(post);  
	        HttpEntity entity = httpResponse.getEntity();  
	        backMes = EntityUtils.toString(entity, "UTF-8").toString();  
	    }catch(Exception e){
	    	
	    }
	    return backMes;
	}
	
	
	/**
	 * @Description : 返回string类型的数据
	 * @Author : makangwei
	 */
	public static String postJson(String url,String params,Map<String,String> headers){
		
		logger.info("--------------------post请求地址："+url);
		logger.info("--------------------post请求json体参数："+params);
		HttpClient client;
		
		try {
			client = getHttpClient();
		} catch (Exception e) {
			logger.info("---------创建httpClient异常----------------");			
			e.printStackTrace();
			return null;
		}
		
		HttpPost post = new HttpPost(url);
		//添加头信息
		for (String key : headers.keySet()) {
			post.addHeader(key, headers.get(key));
		}
		
		HttpEntity entity;
		HttpResponse response;
		try {
			entity = new StringEntity(params);
			post.setEntity(entity);
			response = client.execute(post);
			InputStream is = response.getEntity().getContent();
			int stateCode = response.getStatusLine().getStatusCode();
			String str = IOUtils.convertStreamToString(is);
			logger.info("------------调用返回状态是："+stateCode+"-------------------------");
			logger.info("------------调用返回结果是："+str+"-------------------------");
			return str;
			
		} catch (Exception e) {
			logger.info("---------post请求发生异常----------------");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @Description : 获取httpclient，解决跨域问题
	 * @Author : makangwei
	 * @Date : 2017年8月17日
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 */
	public static HttpClient getHttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException{
		
		SSLContextBuilder builder = new SSLContextBuilder();
		
		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		
		SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(),NoopHostnameVerifier.INSTANCE);
		
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http",new PlainConnectionSocketFactory())
				.register("https",sslConnectionSocketFactory)
				.build();
		
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		
		cm.setMaxTotal(200);
		
		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(sslConnectionSocketFactory)
				.setConnectionManager(cm)
				.build();
		
		return httpClient;
	}

	public static String resentCode(String requestUri, String code) {
		
		logger.info("--------------------get请求参数："+code);
		HttpClient client = null;
		try{
			client = getHttpClient();
		}catch(Exception e){
			logger.info("---------创建httpClient异常----------------");			
			e.printStackTrace();
			return null;
		}
		
		HttpGet get = new HttpGet(requestUri+"?code="+code);
		logger.info("--------------------get请求地址："+requestUri+"?code="+code);
//		//添加头信息
//		for (String key : headers.keySet()) {
//			post.addHeader(key, headers.get(key));
//		}
		
//		HttpEntity entity;
		HttpResponse response;
		try {
			response = client.execute(get);
			InputStream is = response.getEntity().getContent();
			int stateCode = response.getStatusLine().getStatusCode();
			String str = IOUtils.convertStreamToString(is);
			logger.info("------------get调用返回状态是："+stateCode+"-------------------------");
			logger.info("------------get调用返回结果是："+str+"-------------------------");
			
		} catch (Exception e) {
			logger.info("---------get请求发生异常----------------");
			e.printStackTrace();
		}
		return null; //返回结果为空
	}
}
