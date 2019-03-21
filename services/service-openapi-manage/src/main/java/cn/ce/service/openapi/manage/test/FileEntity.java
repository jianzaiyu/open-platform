package cn.ce.service.openapi.manage.test;

import org.springframework.web.multipart.MultipartFile;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2017年9月30日
*/
public class FileEntity {

	private String fileId;
	private String name;
	private String type;
	private Integer size;
	private MultipartFile file;
	
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
     
}
