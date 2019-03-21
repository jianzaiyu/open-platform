package cn.ce.service.openapi.base.util.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ce.service.openapi.base.common.IOUtils;
import cn.ce.service.openapi.base.util.LoggerUtil;


/**
 * 
 * @ClassName: HttpUtils
 * @Description: 发送http请求工具类
 * @create 2018年5月7日/makangwei
 * @update 2018年5月7日/makangwei/(说明。)....多次修改添加多个update
 *
 */
public class HttpUtil {

	private static final Logger _LOGGER = LoggerFactory.getLogger(HttpUtil.class);

	private static final LoggerUtil loggerUtil = new LoggerUtil(_LOGGER);
	
	/**
	 * 
	* @Title: postJson  
	* @Description: 发送json post请求 
	* @create 2018年5月17日/makangwei  
	* @update 2018年5月17日/makangwei/(说明。)....多次修改添加多个update   
	* @param @param url
	* @param @param json body
	* @param @param headers 参数
	* @param @param outputLogger 是否打印请求过程中的详细日志。调用第三方接口建议开启。日志内容较多
	* @param @param timeout 超时时间
	* @param @return    参数  
	* @return HttpResult    返回类型  
	* @throws
	 */
	public static HttpResult postJson(String url, String jsonStr, Map<String, String> headers,boolean outputLogger,int timeout) {

		return putOrPostJson(url, jsonStr, headers, HttpMethod.POST, outputLogger,timeout);

	}
	
	/**
	 * 
	* @Title: postJson  
	* @Description: 发送json post请求 不限制超时时间
	* @create 2018年5月17日/makangwei  
	* @update 2018年5月17日/makangwei/(说明。)....多次修改添加多个update   
	* @param @param url
	* @param @param json body
	* @param @param headers 参数
	* @param @param outputLogger 是否打印请求过程中的详细日志。调用第三方接口建议开启。日志内容较多
	* @param @return    参数  
	* @return HttpResult    返回类型  
	* @throws
	 */
	public static HttpResult postJson(String url, String jsonStr, Map<String, String> headers,boolean outputLogger) {

		return putOrPostJson(url, jsonStr, headers, HttpMethod.POST, outputLogger,0);

	}

	/**
	 * 
	* @Title: postJson  
	* @Description: 发送json put请求 
	* @create 2018年5月17日/makangwei  
	* @update 2018年5月17日/makangwei/(说明。)....多次修改添加多个update   
	* @param @param url
	* @param @param json body
	* @param @param headers 参数
	* @param @param outputLogger 是否打印请求过程中的详细日志。调用第三方接口建议开启。日志内容较多
	* @param @param timeout 超时时间
	* @param @return    参数  
	* @return HttpResult    返回类型  
	* @throws
	 */
	public static HttpResult putJson(String url, String jsonStr, Map<String, String> headers,boolean outputLogger,int timeout) {
		
		return putOrPostJson(url, jsonStr, headers, HttpMethod.POST, outputLogger,timeout);
	}
	
	/**
	 * 
	* @Title: postJson  
	* @Description: 发送json put请求 不限制超时时间
	* @create 2018年5月17日/makangwei  
	* @update 2018年5月17日/makangwei/(说明。)....多次修改添加多个update   
	* @param @param url
	* @param @param json body
	* @param @param headers 参数
	* @param @param outputLogger 是否打印请求过程中的详细日志。调用第三方接口建议开启。日志内容较多
	* @param @return    参数  
	* @return HttpResult    返回类型  
	* @throws
	 */
	public static HttpResult putJson(String url, String jsonStr, Map<String, String> headers,boolean outputLogger) {
		
		return putOrPostJson(url, jsonStr, headers, HttpMethod.POST, outputLogger,0);
	}

	/**
	 * 
	* @Title: postForm  
	* @Description: 发送 post请求。参数放在form表单 
	* @create 2018年5月17日/makangwei  
	* @update 2018年5月17日/makangwei/(说明。)....多次修改添加多个update   
	* @param @param url
	* @param @param formMap 表单参数
	* @param @param headers 头部参数
	* @param @param outputLogger 是否打印请求过程中的详细日志。调用第三方接口建议开启。日志内容较多
	* @param @param timeout 超时时间
	* @param @return    参数  
	* @return HttpResult    返回类型  
	* @throws
	 */
	public static HttpResult postForm(String url, Map<String, String> formMap, Map<String, String> headers, boolean outputLogger,int timeout) {
		
		return putOrPostForm(url, formMap, headers, HttpMethod.POST, outputLogger,timeout);
	}
	
	/**
	 * 
	* @Title: postForm  
	* @Description: 发送 post请求。参数放在form表单 不设置超时时间
	* @create 2018年5月17日/makangwei  
	* @update 2018年5月17日/makangwei/(说明。)....多次修改添加多个update   
	* @param @param url
	* @param @param formMap 表单参数
	* @param @param headers 头部参数
	* @param @param outputLogger 是否打印请求过程中的详细日志。调用第三方接口建议开启。日志内容较多
	* @param @return    参数  
	* @return HttpResult    返回类型  
	* @throws
	 */
	public static HttpResult postForm(String url, Map<String, String> formMap, Map<String, String> headers, boolean outputLogger) {
		
		return putOrPostForm(url, formMap, headers, HttpMethod.POST, outputLogger,0);
	}
	
	/**
	 * 
	* @Title: postForm  
	* @Description: 发送 put请求。参数放在form表单 
	* @create 2018年5月17日/makangwei  
	* @update 2018年5月17日/makangwei/(说明。)....多次修改添加多个update   
	* @param @param url
	* @param @param formMap 表单参数
	* @param @param headers 头部参数
	* @param @param outputLogger 是否打印请求过程中的详细日志。调用第三方接口建议开启。日志内容较多
	* @param @param timeout 超时时间
	* @param @return    参数  
	* @return HttpResult    返回类型  
	* @throws
	 */
	public static HttpResult putForm(String url, Map<String, String> formMap, Map<String, String> headers, boolean outputLogger,int timeout) {

		return putOrPostForm(url, formMap, headers, HttpMethod.PUT, outputLogger,timeout);

	}

	/**
	 * 
	* @Title: postForm  
	* @Description: 发送 put请求。参数放在form表单 不设置超时时间
	* @create 2018年5月17日/makangwei  
	* @update 2018年5月17日/makangwei/(说明。)....多次修改添加多个update   
	* @param @param url
	* @param @param formMap 表单参数
	* @param @param headers 头部参数
	* @param @param outputLogger 是否打印请求过程中的详细日志。调用第三方接口建议开启。日志内容较多
	* @param @return    参数  
	* @return HttpResult    返回类型  
	* @throws
	 */
	public static HttpResult putForm(String url, Map<String, String> formMap, Map<String, String> headers, boolean outputLogger) {

		return putOrPostForm(url, formMap, headers, HttpMethod.PUT, outputLogger,0);

	}
	
	/**
	 * 
	* @Title: get  
	* @Description: get请求 
	* @create 2018年5月17日/makangwei  
	* @update 2018年5月17日/makangwei/(说明。)....多次修改添加多个update   
	* @param @param url
	* @param @param headers
	* @param @param outputLogger 是否打印请求过程中的详细日志。调用第三方接口建议开启。日志内容较多
	* @param @param timeout 超时时间
	* @param @return    参数  
	* @return HttpResult    返回类型  
	* @throws
	 */
	public static HttpResult get(String url,Map<String, String> headers, boolean outputLogger,int timeout) {
		
		return getOrDelete(url, headers, HttpMethod.GET, outputLogger,timeout);
		
	}
	
	/**
	 * 
	* @Title: get请求，不设置超时时间
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @create 2018年5月17日/makangwei  
	* @update 2018年5月17日/makangwei/(说明。)....多次修改添加多个update   
	* @param @param url
	* @param @param headers
	* @param @param outputLogger 是否打印请求过程中的详细日志。调用第三方接口建议开启。日志内容较多
	* @param @return    参数  
	* @return HttpResult    返回类型  
	* @throws
	 */
	public static HttpResult get(String url,Map<String, String> headers, boolean outputLogger) {
		
		return getOrDelete(url, headers, HttpMethod.GET, outputLogger,0);
		
	}

	public static HttpResult get(String url,Map<String, String> headers, Map<String,String> queryParams, boolean outputLogger,int timeout) {
        if (queryParams == null || queryParams.isEmpty()) {
            return get(url,headers,outputLogger,timeout);
        }

        List<NameValuePair> params = new ArrayList<>();

        if (queryParams != null) {
            Set<Map.Entry<String, String>> entries = queryParams.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        
        String query = null;
        try {
			query = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
		} catch (ParseException | IOException e) {
			e.printStackTrace();
			return null;
		}
        
        // TODO general mkw 20180509 缺少参数校验机制。如果url自带了一个参数和query中的参数同名如何处理
        if(url.indexOf('?') > 0){
        	url =  url + "&" + query;
        }else{
        	url = url + "?" + query;
        }
		return getOrDelete(url, headers, HttpMethod.GET, outputLogger,timeout);
		
	}
	
	public static HttpResult get(String url,Map<String, String> headers, Map<String,String> queryParams, boolean outputLogger) {
        
		return get(url,headers,queryParams,outputLogger,0);
	}


	public static HttpResult delete(String url,Map<String, String> headers, boolean outputLogger,int timeout) {

		return getOrDelete(url, headers, HttpMethod.DELETE, outputLogger,timeout);
	}
	
	public static HttpResult delete(String url,Map<String, String> headers, boolean outputLogger) {

		return getOrDelete(url, headers, HttpMethod.DELETE, outputLogger,0);
	}
	
	private static HttpResult putOrPostJson(String url, String jsonStr, Map<String, String> headers, HttpMethod method,
			boolean outputLogger,int timeout) {

		_LOGGER.info("***********在代码中调用http/post or put /json请求***********");
		loggerUtil.info("url:" + url, outputLogger);
		loggerUtil.info("params:" + jsonStr, outputLogger);
		loggerUtil.info("timeout:" + timeout, outputLogger);

		// 创建http client
		HttpClient httpClient = null;
		try {
			httpClient = getHttpClient();
		} catch (Exception e) {
			_LOGGER.error("create httpClient error");
			return null;
		}
		// 添加头信息
		HttpEntityEnclosingRequestBase hrb;
		if (HttpMethod.POST == method) {
			hrb = new HttpPost(url);
		} else if (HttpMethod.PUT == method) {
			hrb = new HttpPut(url);
		} else {
			_LOGGER.info("该方法只支持post和put请求");
			return null;
		}
		
		if (headers != null) {
			for (String key : headers.keySet()) {
				hrb.setHeader(key, headers.get(key));
			}
		}

		// 添加请求体参数
		StringEntity strEntity = new StringEntity(jsonStr, ContentType.APPLICATION_JSON);
		strEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING, "utf-8"));
		hrb.setEntity(strEntity);

		// 调用，并取回返回结果，打印返回状态码
		HttpResponse response = null;
		try {
			response = httpClient.execute(hrb);
		} catch (Exception e) {
			_LOGGER.error("error happens when execute http post stack message as follows:");
			e.printStackTrace();
			return null;
		}

		StatusLine status = response.getStatusLine();
		loggerUtil.info("response status:" + status.getStatusCode(), outputLogger);
		InputStream is = null;
		try {
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			String str = IOUtils.convertStreamToString(is);
			loggerUtil.info("返回body：" + str, outputLogger);
			is.close();
			Header[] hds = response.getAllHeaders();
			_LOGGER.info("************resposne success************");
			return new HttpResult(str, status.getStatusCode(), hds);
		} catch (Exception e) {
			_LOGGER.error("拉取body数据失败");
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}

	}

	private static HttpResult putOrPostForm(String url, Map<String,String> formMap, Map<String, String> headers, HttpMethod method, boolean outputLogger,int timeout){
		

		_LOGGER.info("***********在代码中调用http/post or put /json请求***********");
		loggerUtil.info("url:" + url, outputLogger);
		loggerUtil.info("params:" + formMap, outputLogger);
		loggerUtil.info("timeout:" + timeout, outputLogger);

		
		//创建http client
		HttpClient httpClient = null;
		try{
			httpClient = getHttpClient();
		}catch(Exception  e){
			_LOGGER.info("create httpClient error");
			return null;
		}
		
		//添加头信息
		HttpEntityEnclosingRequestBase hrb;
		if(HttpMethod.POST == method){
			hrb = new HttpPost(url);
		} else if(HttpMethod.PUT == method){
			hrb = new HttpPut(url);
		} else{
			_LOGGER.info("该方法只支持post和put请求");
			return null;
		}
		
		if(headers != null){
			for (String key : headers.keySet()) {
				hrb.setHeader(key,headers.get(key));
			}
		}
		
		//添加参数
		if(null != formMap && formMap.size() > 0){
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			for (String key : formMap.keySet()) {
				NameValuePair pair= new BasicNameValuePair(key,formMap.get(key));
				params.add(pair);
			}
			
			UrlEncodedFormEntity strEntity = null;  
			try {  
				strEntity = new UrlEncodedFormEntity(params, "UTF-8");  
			} catch (UnsupportedEncodingException e) {  
				_LOGGER.error("form表单错误！");  
				e.printStackTrace();  
			} 
			hrb.setEntity(strEntity);
		}
		
		// 调用，并取回返回结果，打印返回状态码
		HttpResponse response = null;
		try {
			response = httpClient.execute(hrb);
		} catch (Exception e) {
			_LOGGER.error("error happens when execute http post stack message as follows:");
			e.printStackTrace();
			return null;
		}

		StatusLine status = response.getStatusLine();
		loggerUtil.info("response status:" + status.getStatusCode(), outputLogger);
		InputStream is = null;
		try {
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			String str = IOUtils.convertStreamToString(is);
			loggerUtil.info("返回body：" + str, outputLogger);
			is.close();
			Header[] hds = response.getAllHeaders();
			_LOGGER.info("************resposne success************");
			return new HttpResult(str, status.getStatusCode(), hds);
		} catch (Exception e) {
			_LOGGER.error("拉取body数据失败");
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}

	
	}
	
	private static HttpResult getOrDelete(String url, Map<String,String> headers, HttpMethod method,boolean outputLogger,int timeout){

		
		loggerUtil.info("***********在代码中调用http/ get or delete /json请求***********",outputLogger);
		loggerUtil.info("url:"+url,outputLogger);
		loggerUtil.info("timeout:" + timeout, outputLogger);

		
		HttpClient httpClient = null;
		try{
			httpClient = getHttpClient();
		}catch(Exception e){
			_LOGGER.info("get httpClient error");
			return null;
		}

		//创建请求对象
		HttpRequestBase hrb;
		if(HttpMethod.DELETE == method){
			hrb = new HttpDelete(url);
		}else if(HttpMethod.GET == method){
			hrb = new HttpGet(url);
		}else{
			_LOGGER.info("只支持delete或get方法");
			return null;
		}
		
		//添加头信息
		if(headers != null){
			for (String key : headers.keySet()) {
				hrb.addHeader(key,headers.get(key));
			}
		}
		
		// 调用，并取回返回结果，打印返回状态码
		HttpResponse response = null;
		try {
			response = httpClient.execute(hrb);
		} catch (Exception e) {
			_LOGGER.error("error happens when execute http post stack message as follows:");
			e.printStackTrace();
			return null;
		}

		StatusLine status = response.getStatusLine();
		loggerUtil.info("response status:" + status.getStatusCode(), outputLogger);
		InputStream is = null;
		try {
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			String str = IOUtils.convertStreamToString(is);
			loggerUtil.info("返回body：" + str, outputLogger);
			is.close();
			Header[] hds = response.getAllHeaders();
			_LOGGER.info("************resposne success************");
			return new HttpResult(str, status.getStatusCode(), hds);
		} catch (Exception e) {
			_LOGGER.error("拉取body数据失败");
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	private static HttpClient getHttpClient()
			throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		SSLContextBuilder builder = new SSLContextBuilder();

		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());

		SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build());

		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", new PlainConnectionSocketFactory()).register("https", sslConnectionSocketFactory)
				.build();

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);

		cm.setMaxTotal(200);

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory)
				.setConnectionManager(cm).build();

		return httpClient;
	}

}
