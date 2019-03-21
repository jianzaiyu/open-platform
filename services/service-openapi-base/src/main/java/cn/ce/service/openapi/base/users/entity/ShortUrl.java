package cn.ce.service.openapi.base.users.entity;


/**
 *
 * @Title: ShortUrl.java
 * @Description
 * @version V1.0
 * @author yang.yu@300.cn
 * @date dat2017年10月23日 time上午10:26:58
 *
 **/
public class ShortUrl {

	private String id;
	/* 长连接 */
	private String longUrl;

	/* 短连接 */
	private String shortUrt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortUrt() {
		return shortUrt;
	}

	public void setShortUrt(String shortUrt) {
		this.shortUrt = shortUrt;
	}

}
