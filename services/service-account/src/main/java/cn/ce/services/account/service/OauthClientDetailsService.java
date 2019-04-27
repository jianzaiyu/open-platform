package cn.ce.services.account.service;

import cn.ce.services.account.entity.OauthClientDetails;

import java.util.List;

/**
*@auther ggs
*@date 2019-04-26 16:11:04.884
*/
public interface OauthClientDetailsService {
    int deleteByPrimaryKey(String clientId);

    int insert(OauthClientDetails record);

    int insertSelective(OauthClientDetails record);

    OauthClientDetails selectByPrimaryKey(String clientId);

    int updateByPrimaryKeySelective(OauthClientDetails record);

    int updateByPrimaryKey(OauthClientDetails record);

    List<OauthClientDetails> selectByClientType(String clientType);
}