package cn.ce.services.account.service;

import cn.ce.services.account.entity.User;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


/**
 * @author: ggs
 * @date: 2019-03-01 14:57
 **/
public interface UserService {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String name);

    User selectByEmail(String email);

    User selectByTelNumber(String telNumber);

    User selectByUserNameAndEmail(String userName, String email);

    int updateByUserNameSelective(User record);
}
