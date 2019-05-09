package cn.ce.services.auth.service;

import cn.ce.services.auth.entity.Member;

/**
 * @author: ggs
 * @date: 2019-05-09 15:36
 **/
public interface MemberService {
    Member queryByUserName(String userName);
}
