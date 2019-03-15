package cn.ce.services.account.service;

import cn.ce.services.account.entity.Role;

/**
 * @author: ggs
 * @date: 2019-03-06 18:58
 **/
public interface RoleService {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}
