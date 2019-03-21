package cn.ce.service.openapi.base.util;//package cn.ce.service.openapi.base.util;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.dom4j.Document;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.alibaba.fastjson.JSON;
//
///***
// * 
// * 
// * @ClassName:  SmsUtil   
// * @Description: 发送短信工具类   
// * @author: lida 
// * @date:   2017年10月11日 下午3:11:41   
// * @Copyright: 2017 中企动力科技股份有限公司 © 1999-2017 300.cn All Rights Reserved
// *
// */
//public class SmsUtil {
//
//	private static String webSiteId;
//	
//	private static String sign;
//	
//	private static String providerId;
//
//	
//	private static final Logger log = LoggerFactory.getLogger(SmsUtil.class);
//	
//	
//
//	public SmsUtil(String webSiteId,String sign,String providerId) {
//		SmsUtil.webSiteId = webSiteId;
//		SmsUtil.sign = sign;
//		SmsUtil.providerId = providerId;
//	}
//
//
//	/***
//	 * 
//	 * @Title: send
//	 * @Description: 异步发送方法
//	 * @param : @param phone：手机号码
//	 * @param : @param content：发送内容
//	 * @return: void
//	 * @throws
//	 */
//	public static void notSyncSend(final String phone, final String content) {
//		new Thread() {
//			public void run() {
//				messageSend(phone, content);
//			};
//		}.start();
//	}
//
//	/***
//	 * 
//	 * @Title: messageSend
//	 * @Description: 发送短信方法
//	 * @param : @param phone 手机号码
//	 * @param : @param content 发送内容
//	 * @return: boolean true为发送成功 false为发送失败
//	 * @throws
//	 */
//	public static boolean messageSend(String phone, String content) {
//		boolean retBo = false;
//		Map<String,String> headers = new HashMap<>();
//		String username = propertiesUtil.getValue("sms.username");
//		String pwd = propertiesUtil.getValue("sms.pwd");
//		String uri = propertiesUtil.getValue("sms.uri");
//		try {
//			headers.put("Authorization", "Basic " + Base64Utils.encode((username + ":" + pwd).getBytes()));
//			List<NameValuePair> params = new ArrayList<NameValuePair>();
//			params.add(new BasicNameValuePair("content", content));
//			params.add(new BasicNameValuePair("phone", phone));
//			
//			if(StringUtils.isNotBlank(webSiteId)){
//				params.add(new BasicNameValuePair("websiteid", webSiteId));
//			}
//			
//			if(StringUtils.isNotBlank(sign)){
//				params.add(new BasicNameValuePair("sign", sign));
//			}
//			
//			if(StringUtils.isNotBlank(providerId)){
//				params.add(new BasicNameValuePair("providerId", providerId));
//			}
//			
//			log.info("sendSms param:" + JSON.toJSONString(params));
//			
//			String postJson = "";
//			
//			// TODO 
//			//生产环境时调用短信网关
//			if(StringUtils.isNotBlank(webSiteId) && StringUtils.isNotBlank(sign) && "2" == providerId.trim()){
//				postJson = HttpUtils.postJsonNew(uri, params, headers);
//			}else{
//			//非生产环境写死报文	
//				postJson = "<response><phone>*****</phone><oid>1102</oid><status>1</status></response>";
//			}
//			
//			log.info("sendSms returnXml:" + postJson);
//			
//            Document doc = DocumentHelper.parseText(postJson);
//            if(doc != null){
//            	Element rootElt = doc.getRootElement();
//            	String status = rootElt.element("status").getStringValue();
//            	if("1" == status || "1".equals(status)){
//	        		log.info("sendSms success!");
//	        		retBo = true;
//	        	}
//            }
//            
//		} catch (Exception e) {
//			log.error("sendSms error,e:" + e.toString());
//			e.printStackTrace();
//		}
//		
//		return retBo;
//	}
//	
//}
