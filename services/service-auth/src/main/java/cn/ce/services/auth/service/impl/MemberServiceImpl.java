package cn.ce.services.auth.service.impl;

import cn.ce.services.auth.entity.Member;
import cn.ce.services.auth.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @author: ggs
 * @date: 2019-05-09 15:36
 **/
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Member queryByUserName(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("memberCode").is(userName));
        return mongoTemplate.findOne(query, Member.class);
    }
}
