package cn.ce.services.account.service;

import cn.ce.services.account.entity.Identify;

/**
 * @author ggs
 * @date 2019/3/6 22:58
 */
public interface IdentifyService {
    int deleteByPrimaryKey(Integer id);

    int insert(Identify record);

    int insertSelective(Identify record);

    Identify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Identify record);

    int updateByPrimaryKey(Identify record);

    Identify selectByUserId(Integer uid);

    Identify selectByCardNumber(String cardNumber);
}
