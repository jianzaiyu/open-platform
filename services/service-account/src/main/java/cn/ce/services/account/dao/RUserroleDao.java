package cn.ce.services.account.dao;

import cn.ce.services.account.entity.RUserrole;

/**
*@auther ggs
*@date 2019-03-01 13:39:28.950
*/
public interface RUserroleDao {
    int deleteByPrimaryKey(Integer urId);

    int insert(RUserrole record);

    int insertSelective(RUserrole record);

    RUserrole selectByPrimaryKey(Integer urId);

    int updateByPrimaryKeySelective(RUserrole record);

    int updateByPrimaryKey(RUserrole record);
}