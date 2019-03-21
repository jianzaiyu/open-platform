package cn.ce.service.openapi.base.admin.dao;

import cn.ce.service.openapi.base.users.entity.User;


/**
 * 
 * 
 * @ClassName:  IAdminDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: makangwei
 * @date:   2017年9月29日 上午9:47:56   
 * @Copyright: 2017 中企动力科技股份有限公司 © 1999-2017 300.cn All Rights Reserved
 *
 */
public interface IAdminDao {
	
	User checkLogin(String username, String password);
	
    User findByUserName(String userName);
    
    void update(User admin);
    
    void createNewDefaultAdmin();
    
}
