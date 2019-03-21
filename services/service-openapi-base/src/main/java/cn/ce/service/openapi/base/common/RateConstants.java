package cn.ce.service.openapi.base.common;

/**
* @Description : 频次四个档位
* @Author : makangwei
* @Date : 2017年10月17日
*/
public class RateConstants {

	public static final Integer TYPE_1 = 1;
	public static final Integer TYPE_2 = 2;
	public static final Integer TYPE_3 = 3;
	public static final Integer TYPE_4 = 4;
	
	public static final Integer TYPE_1_RATE = 200;
	public static final Integer TYPE_1_PER = 1;
	public static final Integer	TYPE_1_QUOTA_MAX = 100000;
	public static final Integer TYPE_1_QUOTA_RENEW_RATE = 86400;
	
	public static final Integer TYPE_2_RATE = 500;
	public static final Integer TYPE_2_PER = 1;
	public static final Integer	TYPE_2_QUOTA_MAX = 500000;
	public static final Integer TYPE_2_QUOTA_RENEW_RATE = 86400;
	
	public static final Integer TYPE_3_RATE = 1000;
	public static final Integer TYPE_3_PER = 1;
	public static final Integer	TYPE_3_QUOTA_MAX = 1000000;
	public static final Integer TYPE_3_QUOTA_RENEW_RATE =  86400;
	
}
