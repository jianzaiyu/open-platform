package cn.ce.services.auth.service;

import cn.ce.services.auth.entity.Identify;

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
}
