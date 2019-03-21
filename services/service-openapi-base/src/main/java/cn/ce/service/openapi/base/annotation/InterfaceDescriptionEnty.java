package cn.ce.service.openapi.base.annotation;

/**
 * 自定义标签实体类
 * 
 * @author lenovo
 *
 */
public class InterfaceDescriptionEnty {

	private String name;
	private String des;
	private String version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
