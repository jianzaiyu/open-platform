package cn.ce.service.openapi.base.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * httpClient 连接类
 */
public class HttpClientUtilsNew {
    /** 字符编码-UTF8 */
    private static final String CHARSET_UTF8 = "UTF-8";

    /**
     * 获取远程请求响应结果字符串
     * @param url  请求URL
     * @param params 请求参数
     * @return 响应结果字符串
     * @throws ClientProtocolException 请求Exception
     * @throws IOException IOException
     */
    public static String getResponseString(String url, String params) throws ClientProtocolException, IOException {
        HttpPost httpPost = getHttpPost(url, params);
        String responseContent = getResponseString(httpPost);

        return responseContent;
    }

    /**
     * 获取远程请求响应结果字符串
     * @param url  请求URL
     * @param params 请求参数
     * @return 响应结果字符串
     * @throws ClientProtocolException 请求Exception
     * @throws IOException IOException
     */
    public static String getResponseString1(String url, String params) throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(params, CHARSET_UTF8);
        httpPost.setEntity(stringEntity);
        String responseContent = getResponseString(httpPost);

        return responseContent;
    }
    
  


    /**
     * 获取远程请求响应结果字符串
     * @param url  请求URL
     * @param paramsMap 请求参数
     * @return 响应结果字符串
     * @throws ClientProtocolException 请求Exception
     * @throws IOException IOException
     */
    public static String getResponseString(String url, Map<String, String> paramsMap)
            throws ClientProtocolException, IOException {
    	
        HttpPost httpPost = getHttpPost(url, paramsMap);
        String responseContent = getResponseString(httpPost);

        return responseContent;
    }

    /**
     * 获取远程请求响应结果字符串
     * @param url  请求URL
     * @param params 请求参数
     * @return 响应结果流
     * @throws ClientProtocolException 请求Exception
     * @throws IOException IOException
     */
    public static InputStream getResponseInputStream(String url, String params)
            throws ClientProtocolException, IOException {
        HttpPost httpPost = getHttpPost(url, params);
        InputStream response = getResponseInputStream(httpPost);

        return response;
    }

    /**
     * 获取远程请求响应结果字符串
     * @param url  请求URL
     * @param paramsMap 请求参数
     * @return 响应结果流
     * @throws ClientProtocolException 请求Exception
     * @throws IOException IOException
     */
    public static InputStream getResponseInputStream(String url, Map<String, String> paramsMap)
            throws ClientProtocolException, IOException {
        HttpPost httpPost = getHttpPost(url, paramsMap);
        InputStream responseContent = getResponseInputStream(httpPost);

        return responseContent;
    }

    private static HttpPost getHttpPost(String url, List<NameValuePair> postParams)
            throws UnsupportedEncodingException {
        // 创建httppost
        HttpPost httpPost = new HttpPost(url);
        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(postParams, CHARSET_UTF8);
        httpPost.setEntity(uefEntity);
        return httpPost;
    }

    private static HttpPost getHttpPost(String url, String params)
            throws UnsupportedEncodingException {
        // 创建参数队列
        List<NameValuePair> postParams = HttpClientUtilsNew.getPostParams(params);
        HttpPost httpPost = getHttpPost(url, postParams);
        return httpPost;
    }

    private static HttpPost getHttpPost(String url, Map<String, String> paramsMap)
            throws UnsupportedEncodingException {
        // 创建参数队列
        List<NameValuePair> postParams = HttpClientUtilsNew.getPostParams(paramsMap);
        HttpPost httpPost = getHttpPost(url, postParams);
        return httpPost;
    }

    private static String getResponseString(HttpRequestBase httpPost)
            throws ClientProtocolException, IOException {
        String responseContent = null;

        // 创建httpClient实例.
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseContent = EntityUtils.toString(entity, CHARSET_UTF8);
                }
            } finally {
                closeResponse(response);
            }
        } finally {
            closeConnection(httpClient);
        }

        return responseContent;
    }
    
  

    private static InputStream getResponseInputStream(HttpRequestBase httpPost)
            throws ClientProtocolException, IOException {
        InputStream responseIs = null;

        // 创建httpClient实例.
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseIs = entity.getContent();
                }
            } finally {
                closeResponse(response);
            }
        } finally {
            closeConnection(httpClient);
        }

        return responseIs;
    }

    // 创建参数队列
    public static List<NameValuePair> getPostParams(String params) {
        List<NameValuePair> postParams = new ArrayList<NameValuePair>();
        if (params == null || params.trim().isEmpty()) {
            return postParams;
        }
        String[] paramPairs = params.split("&");
        for (int i = 0; i < paramPairs.length; i++) {
            String[] pairArr = paramPairs[i].split("=");
            if (pairArr.length < 2) {
                continue;
            }
            postParams.add(new BasicNameValuePair(pairArr[0], pairArr[1]));
        }
        return postParams;
    }

    // 创建参数队列
    public static List<NameValuePair> getPostParams(Map<String, String> paramsMap) {
        List<NameValuePair> postParams = new ArrayList<NameValuePair>();
        if (paramsMap == null || paramsMap.isEmpty()) {
            return postParams;
        }
        Set<String> keySet = paramsMap.keySet();
        for (String key : keySet) {
            postParams.add(new BasicNameValuePair(key, paramsMap.get(key)));
        }
        return postParams;
    }

    public static void closeResponse(CloseableHttpResponse response) throws IOException {
        if (response != null) {
            response.close();
        }
    }

    public static void closeConnection(CloseableHttpClient httpClient) throws IOException {
        if (httpClient != null) {
            // 关闭流并释放资源
            httpClient.close();
        }
    }

}
