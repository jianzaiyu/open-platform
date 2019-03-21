package cn.ce.service.openapi.base.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;

/**
 * 
 * @ClassName: Util
 * @Description: 常用工具类型
 * @author dingjia@300.cn
 *
 */
public class Util {
    
    /** 哈希算法 */
    private static final String MAC_NAME = "HmacSHA1";  
    /** 编码类型 */
    private static final String ENCODING = "UTF-8";   
    
   /* public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception  {           
        byte[] data = encryptKey.getBytes(ENCODING);  
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称  
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);   
        //生成一个指定 Mac 算法 的 Mac 对象  
        Mac mac = Mac.getInstance(MAC_NAME);   
        //用给定密钥初始化 Mac 对象  
        mac.init(secretKey);    
          
        byte[] text = encryptText.getBytes(ENCODING);    
        //完成 Mac 操作   
        return mac.doFinal(text);    
    }
    */
    public static String getRandomStrs(int size) {
        return RandomStringUtils.randomAlphanumeric(size).toUpperCase();
    }
    
    public static String toBase64Str(byte [] data) {
        Base64 b = new Base64();
        return new String(b.encode(data));
    }
    
    public final static String MD5(String s) {
        char [] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char [] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
