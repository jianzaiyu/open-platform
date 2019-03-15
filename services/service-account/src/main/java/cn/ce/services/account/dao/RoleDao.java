package cn.ce.services.account.dao;

import cn.ce.services.account.entity.Role;

/**
*@auther ggs
*@date 2019-03-05 11:20:48.440
*/
public interface RoleDao {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}