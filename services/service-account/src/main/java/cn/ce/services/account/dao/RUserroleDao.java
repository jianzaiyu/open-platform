package cn.ce.services.account.dao;

import cn.ce.services.account.entity.RUserrole;
import cn.ce.services.account.entity.UserRoleDetail;

import java.util.List;

/**
*@auther ggs
*@date 2019-03-05 11:20:48.431
*/
public interface RUserroleDao {
    int deleteByPrimaryKey(Integer urId);

    int insert(RUserrole record);

    int insertSelective(RUserrole record);

    RUserrole selectByPrimaryKey(Integer urId);

    List<UserRoleDetail> selectByUId(Integer uid);

    int updateByPrimaryKeySelective(RUserrole record);

    int updateByPrimaryKey(RUserrole record);
}