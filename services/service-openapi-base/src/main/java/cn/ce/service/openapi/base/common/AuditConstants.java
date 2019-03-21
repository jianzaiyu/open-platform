package cn.ce.service.openapi.base.common;

/**
 * @Description : 状态相关的常量
 * @Author : makangwei
 * @Date : 2017年10月11日
 */
public class AuditConstants {

	/********** 提供者 提供的开发应用审核状态 **********/
	public static final Integer OPEN_APPLY_UNCHECKED = 0; // 未审核
	public static final Integer OPEN_APPLY_CHECKED_COMMITED = 1; // 待审核
	public static final Integer OPEN_APPLY_CHECKED_SUCCESS = 2; // 审核成功
	public static final Integer OPEN_APPLY_CHECKED_FAILED = 3; // 审核失败

	/********** 用户状态 **********/
	public static final int USER__UNCHECKED = 0; // 未审核
	public static final int USER__COMMITED = 1; // 待审核
	public static final int USER__CHECKED_SUCCESS = 2; // 审核成功
	public static final int USER__CHECKED_FAILED = 3; // 审核失败
	
	public static final int USER_STATE_ENABLE = 1;
	public static final int USER_STATE_DISABLE = 0;
	
	public static final int USER_STATE_OFF = 0; //禁用
	
	public static final int USER_STATE_ON = 1; //启用

	/********** 用户角色 **********/
	public static final int USER_ADMINISTRATOR = 0; // 管理员
	public static final int USER_DEVELOPER = 1; // 开发者
	public static final int USER_PROVIDER = 2; // 提供者

	/********** api审核状态 **********/
	public static final int API_CHECK_STATE_UNCOMMITED = 0; // 未审核
	public static final int API_CHECK_STATE_UNAUDITED = 1; // 待审核
	public static final int API_CHECK_STATE_SUCCESS = 2; // 审核通过
	public static final int API_CHECK_STATE_DENY = 3; // 审核不通过

	/********** 指南审核状态 **********/
	public static final int GUIDE_UNCHECKED = 0; // 未审核
	public static final int GUIDE_COMMITED = 1; // 待审核
	public static final int GUIDE_SUCCESS = 2; // 审核成功
	public static final int GUIDE_FAILED = 3; // 审核失败
	

	/********** 提供者 提供的开发应用审核状态 **********/
	public static final int DIY_APPLY_UNCHECKED = 0; // 未审核
	public static final int DIY_APPLY_CHECKED_COMMITED = 1; // 待审核
	public static final int DIY_APPLY_CHECKED_SUCCESS = 2; // 审核成功
	public static final int DIY_APPLY_CHECKED_FAILED = 3; // 审核失败
	
	
	/********** 网关相关状态 **********/
	public static final boolean GATEWAY_API_VERSIONED_TRUE = true; //开启多版本
	public static final boolean GATEWAY_API_VERSIONED_FALSES = false; //关闭多版本
	public static final String GATEWAT_DEFAULT = "default";
	
	/********** 租户接口状态状态 **********/
	public static final int INTERFACE_RETURNSATAS_SUCCESS=200;
	public static final int INTERFACE_RETURNSATAS_FAILE=110;
	
	/********** api来源 **********/
	public static final Integer API_SOURCE_IMPORT = 1;
	public static final Integer API_SOURCE_TYPEIN = 0;
	
	/********** 文件大小控制 **********/
	public static final long MAX_UPLOAD_SIZE= 2097152; // 2M=2097152bytes
	
	public static final String STATS_BUCKET = "2";
	
}
