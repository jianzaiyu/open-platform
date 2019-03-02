package cn.ce.services.account.dao;

import cn.ce.services.account.entity.Oauth;

/**
*@auther ggs
*@date 2019-03-01 13:39:28.959
*/
public interface OauthDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Oauth record);

    int insertSelective(Oauth record);

    Oauth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Oauth record);

    int updateByPrimaryKey(Oauth record);
}