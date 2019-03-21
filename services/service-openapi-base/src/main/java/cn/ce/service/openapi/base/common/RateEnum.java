package cn.ce.service.openapi.base.common;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2017年10月17日
*/
public enum RateEnum {

	  RATE1(1)
	  ,RATE2(2)
	  ,RATE3(3)
	  ;
	 
    private int value;
 
    private RateEnum(int num) {
        this.value = num;
    }
 
    public int toValue() {
        return value;
    }
}
