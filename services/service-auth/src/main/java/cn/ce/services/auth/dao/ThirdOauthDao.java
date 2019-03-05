package cn.ce.services.auth.dao;

import cn.ce.services.auth.entity.ThirdOauth;

/**
*@auther ggs
*@date 2019-03-05 11:20:48.439
*/
public interface ThirdOauthDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ThirdOauth record);

    int insertSelective(ThirdOauth record);

    ThirdOauth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThirdOauth record);

    int updateByPrimaryKey(ThirdOauth record);
}