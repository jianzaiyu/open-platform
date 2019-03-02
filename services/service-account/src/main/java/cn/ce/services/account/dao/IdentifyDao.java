package cn.ce.services.account.dao;

import cn.ce.services.account.entity.Identify;

/**
*@auther ggs
*@date 2019-03-01 13:39:28.958
*/
public interface IdentifyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Identify record);

    int insertSelective(Identify record);

    Identify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Identify record);

    int updateByPrimaryKey(Identify record);
}