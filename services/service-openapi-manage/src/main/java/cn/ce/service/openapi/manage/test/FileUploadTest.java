package cn.ce.service.openapi.manage.test;//package cn.ce.service.openapi.manage.test;
//
//import java.io.File;
//import java.io.IOException;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.mongodb.gridfs.GridFS;
//import com.mongodb.gridfs.GridFSInputFile;
//
//import cn.ce.service.openapi.base.common.CloudResult;
//
///**
// * @Description : 说明
// * @Author : makangwei
// * @Date : 2017年9月29日
// */
//
//@RestController
//@RequestMapping("/test")
//public class FileUploadTest {
//
//	@Resource
//	MongoTemplate mongoTemplate;
//
//	private static final Logger _LOGGER = LoggerFactory.getLogger(FileUploadTest.class);
//
//	@RequestMapping(value="/testFileUpload",method=RequestMethod.POST)
//	public CloudResult<String> testFileUpload(
//			HttpServletRequest request,
//			@RequestParam(required=true) String password,
//			@RequestParam(required=true) String userName,
//			@RequestParam(required=true) String path,
//			@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
//		
//
//		CloudResult<String> result = new CloudResult<String>();
//		
//		System.out.println(file.getOriginalFilename());
//		System.out.println(userName);
//		System.out.println(password);
//		
//		 //先将文件缓存到服务器上
//		String realPath = request.getSession().getServletContext().getRealPath(file.getOriginalFilename());
//		System.out.println("realPath:"+realPath);
//		
//		
//		File file2 = new File(realPath);
//		try{
//			 file.transferTo(file2);
//		}catch(Exception e){
//			_LOGGER.info("文件缓存到本地时候发生了异常");
//			result.setErrorMessage("文件缓存到本地发生了一场");
//			return result; 
//		}
//		
//		GridFS gfsDoc = new GridFS(mongoTemplate.getDb(),"API_INSTRUCTIONS");
//		
//		GridFSInputFile gfsFile = gfsDoc.createFile(file2);
//		
//		gfsFile.put("whose", "whose");
//		gfsFile.put("userName", "zhangsan");
//		gfsFile.put("password", "123123");
//		
//		gfsFile.save();
//		
//		if(file2.exists()){
//			file2.delete();
//		}
//		result.setSuccessMessage("修改成功");
//		return result;
//	}
//}
