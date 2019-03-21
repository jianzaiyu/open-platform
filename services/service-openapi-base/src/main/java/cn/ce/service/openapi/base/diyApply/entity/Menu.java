package cn.ce.service.openapi.base.diyApply.entity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年3月7日
*/
public class Menu {
	
	private Long id;
	private String name; // 名称，选填
	private Integer level; // 级别，必填
	private Long tenantId; // 租户id，必填
	private Long appId;
	private String url; // 链接，必填
	// parentId,beforeMenuId,behandMenuId 为三选一 必填
	private Long parentId;// 父定制化应用id 选填
	private Long beforeMenuId;// 放在 标品菜单前 选填
	private Long behandMenuId;// 放在标品菜单id 后 选填
	private Integer point;// before 或after 之后的 对比标品菜单 坐标点 必填
	private Integer leaf;// 是否是叶子节点 1是,0否 必填
	private String code;// 菜单code 必填 唯一
	private Integer frame = 1;// 是否用ifarme 必填 1为iframe嵌入，0为非嵌入
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getBeforeMenuId() {
		return beforeMenuId;
	}
	public void setBeforeMenuId(Long beforeMenuId) {
		this.beforeMenuId = beforeMenuId;
	}
	public Long getBehandMenuId() {
		return behandMenuId;
	}
	public void setBehandMenuId(Long behandMenuId) {
		this.behandMenuId = behandMenuId;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getLeaf() {
		return leaf;
	}
	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getFrame() {
		return frame;
	}
	public void setFrame(Integer frame) {
		this.frame = frame;
	}
	
	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", level=" + level + ", tenantId=" + tenantId + ", appId=" + appId
				+ ", url=" + url + ", parentId=" + parentId + ", beforeMenuId=" + beforeMenuId + ", behandMenuId="
				+ behandMenuId + ", point=" + point + ", leaf=" + leaf + ", code=" + code + ", frame=" + frame + "]";
	}
	
}
