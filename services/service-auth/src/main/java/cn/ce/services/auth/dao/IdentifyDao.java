package cn.ce.services.auth.dao;

import cn.ce.services.auth.entity.Identify;

/**
*@auther ggs
*@date 2019-03-05 11:20:48.438
*/
public interface IdentifyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Identify record);

    int insertSelective(Identify record);

    Identify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Identify record);

    int updateByPrimaryKey(Identify record);
}