package cn.ce.services.auth.service;

import cn.ce.services.auth.entity.RUserrole;
import cn.ce.services.auth.entity.UserRoleDetail;

import java.util.List;

/**
 * @author ggs
 * @date 2019/3/6 22:30
 */
public interface RUserroleService {
    int deleteByPrimaryKey(Integer urId);

    int insert(RUserrole record);

    int insertSelective(RUserrole record);

    RUserrole selectByPrimaryKey(Integer urId);

    List<UserRoleDetail> selectByUId(Integer uid);

    int updateByPrimaryKeySelective(RUserrole record);

    int updateByPrimaryKey(RUserrole record);
}
