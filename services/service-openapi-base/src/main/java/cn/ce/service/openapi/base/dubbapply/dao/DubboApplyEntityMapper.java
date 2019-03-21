package cn.ce.service.openapi.base.dubbapply.dao;

import cn.ce.service.openapi.base.dubbapply.entity.DubboApplyEntity;

public interface DubboApplyEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dubbo_apply
     *
     * @mbg.generated Fri Mar 16 17:26:53 CST 2018
     */
    int deleteByPrimaryKey(Integer pkid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dubbo_apply
     *
     * @mbg.generated Fri Mar 16 17:26:53 CST 2018
     */
    int insert(DubboApplyEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dubbo_apply
     *
     * @mbg.generated Fri Mar 16 17:26:53 CST 2018
     */
    int insertSelective(DubboApplyEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dubbo_apply
     *
     * @mbg.generated Fri Mar 16 17:26:53 CST 2018
     */
    DubboApplyEntity selectByPrimaryKey(Integer pkid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dubbo_apply
     *
     * @mbg.generated Fri Mar 16 17:26:53 CST 2018
     */
    int updateByPrimaryKeySelective(DubboApplyEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dubbo_apply
     *
     * @mbg.generated Fri Mar 16 17:26:53 CST 2018
     */
    int updateByPrimaryKey(DubboApplyEntity record);
}