package cn.ce.service.openapi.base.common;

import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2017年8月17日
*/
public class HttpUtils {

	
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	/**
	 * 
	 * @Description : post请求，传递json参数
	 * @Author : makangwei
	 * @Date : 2017年8月17日
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param headers 请求头，没有的话传空
	 * @return
	 */
	public static String postJson(String url,String params,Map<String,String> headers){
		
		HttpClient client;
		
		try {
			client = getHttpClient();
		} catch (Exception e) {
			logger.info("---------创建httpClient异常----------------");			
			e.printStackTrace();
			return null;
		}
		
		HttpPost post = new HttpPost();
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
			String str = IOUtils.convertStreamToString(is);
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
}
