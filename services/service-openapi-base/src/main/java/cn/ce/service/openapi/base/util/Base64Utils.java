package cn.ce.service.openapi.base.util;

import java.io.ByteArrayInputStream; 
 import java.io.ByteArrayOutputStream; 
 import java.io.File; 
 import java.io.FileInputStream; 
 import java.io.FileOutputStream; 
 import java.io.InputStream; 
 import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;
 
/**
 *  
 * @ClassName: Base64Utils
 * @Description: 处理编码类型
 * @author dingjia@300.cn
 *
 */
public class Base64Utils { 
    /** 常量  */
    private static final int CACHE_SIZE = 1024; 
     
    public static byte [] decode(String base64) throws Exception { 
        Base64 worker = new Base64();  
        return worker.decode(base64.getBytes()); 
    } 
     
    public static String encode(byte [] bytes) throws Exception { 
        Base64 worker = new Base64();  
        return new String(worker.encode(bytes)); 
    } 
     
    public static String encodeFile(String filePath) throws Exception { 
        byte [] bytes = fileToByte(filePath); 
        return encode(bytes); 
    } 
     
    public static void decodeToFile(String filePath, String base64) throws Exception { 
        byte [] bytes = decode(base64); 
        byteArrayToFile(bytes, filePath); 
    } 
     
    public static byte [] fileToByte(String filePath) throws Exception { 
        byte [] data = new byte [ 0 ]; 
        File file = new File(filePath); 
        if (file.exists()) { 
            FileInputStream in = new FileInputStream(file); 
            ByteArrayOutputStream out = new ByteArrayOutputStream(2048); 
            byte [] cache = new byte [CACHE_SIZE]; 
            int nRead = 0; 
            while ((nRead = in.read(cache)) != -1) { 
                out.write(cache, 0, nRead); 
                out.flush(); 
            } 
            out.close(); 
            in.close(); 
            data = out.toByteArray(); 
         } 
        return data; 
    } 
     
    public static void byteArrayToFile(byte [] bytes, String filePath) throws Exception { 
        InputStream in = new ByteArrayInputStream(bytes);   
        File destFile = new File(filePath); 
        if (!destFile.getParentFile().exists()) { 
            destFile.getParentFile().mkdirs(); 
        } 
        destFile.createNewFile(); 
        OutputStream out = new FileOutputStream(destFile); 
        byte [] cache = new byte [CACHE_SIZE]; 
        int nRead = 0; 
        while ((nRead = in.read(cache)) != -1) {   
            out.write(cache, 0, nRead); 
            out.flush(); 
        } 
        out.close(); 
        in.close(); 
    }
      
} 