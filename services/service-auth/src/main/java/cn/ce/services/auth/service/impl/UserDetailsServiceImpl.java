package cn.ce.services.auth.service.impl;

import cn.ce.services.auth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ggs
 * @date: 2019-03-05 11:27
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
//    @Autowired
//    private RUserroleDao rUserroleDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<User> users = jdbcTemplate.query("select userName, password from user where userName = ?", new String[]{userName}, new BeanPropertyRowMapper<>(User.class));
//        User user = userDao.selectByUserName(userName);
        if (users.size() > 0) {
            User user = users.get(0);
//            List<UserRoleDetail> userRoleDetails = rUserroleDao.selectByUId(user.getId());
            List<GrantedAuthority> authorities = new ArrayList<>();
//            for (UserRoleDetail userRoleDetail : userRoleDetails) {
//                authorities.add(new SimpleGrantedAuthority(userRoleDetail.getRoleName()));
//            }
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), authorities
            );
        }
        throw new UsernameNotFoundException("User " + userName + " not found!");
    }
}
