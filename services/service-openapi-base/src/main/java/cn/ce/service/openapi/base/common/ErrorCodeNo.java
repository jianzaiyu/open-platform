package cn.ce.service.openapi.base.common;

/**
 *
 * @author makangwei
 * 2017-8-1
 */
public enum ErrorCodeNo {


	SYS000("系统正常")
	,SYS001("系统错误")
	,SYS003("用户未登录")
	,SYS004("程序发生未处理的异常")
	,SYS005("部分参数为空")
	,SYS006("当前查询结果不存在")
	,SYS007("当前内容不为空")
	,SYS008("部分参数错误")
	,SYS009("已存在")
	,SYS010("不可重复")
	,SYS011("数据已过期")
	,SYS012("状态不正确")
	,SYS013("权限不足")
	,SYS014("调用网关发生异常")
	,SYS015("数据不存在")
	,SYS016("数据不一致")
	,SYS017("暂不可用")
	,SYS018("调用产品中心发生异常")
	,SYS019("session已过期")
	,SYS020("账号不存在")
	,SYS021("密码错误")
	,SYS022("账号或密码错误")
	,SYS023("状态禁用")
	,SYS024("产品码不正确")
	,SYS025("旧版产品码，不支持注册菜单。请申请新的产品码")
	,SYS026("当前版本已经存在")
	,SYS027("网络校验失败") //添加网关地址校验失败
	,SYS028("用户信息错误") //用户信息错误
	,SYS029("调用菜单中心异常，请检查参数")
	,SYS030("审核失败")
	,SYS031("批量更新dubbo数据失败")
	,SYS032("传入数据格式错误")
	,SYS033("环境不支持")
	,SYS034("删除失败")
	,SYS035("已删除")
	,SYS036("参数复制异常")
	,SYS037("BI数据统计异常")
	,SYS038("es服务器连接异常")
	,DOWNLOAD001("部分id不存在")
	,DOWNLOAD002("开放应用id不存在")
	,DOWNLOAD003("文件格式错误")
	,UPLOAD001("文档格式错误") //导入的时候文档格式错误
	,UPLOAD002("文件大小错误"),
	;
	
	
	private String desc;
//	private String codeNo;
	
//	private ErrorCodeNo(String codeNo,String desc){
//		this.desc = desc;
//		this.codeNo = codeNo;
//	}
	
	private ErrorCodeNo(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return desc;
	}

//	public String getCodeNo() {
//		return codeNo;
//	}
	
}
