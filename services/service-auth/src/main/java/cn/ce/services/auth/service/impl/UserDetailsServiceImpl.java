package cn.ce.services.auth.service.impl;

import cn.ce.services.auth.entity.Member;
import cn.ce.services.auth.entity.User;
import cn.ce.services.auth.service.MemberService;
import cn.ce.services.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private UserService userService;
    @Autowired
    private MemberService memberService;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        List<User> users = jdbcTemplate.query("select userName, password from user where userName = ?",
//                new String[]{userName}, new BeanPropertyRowMapper<>(User.class));
        List<GrantedAuthority> authorities = new ArrayList<>();
        User user = userService.selectByUserName(userName);
        if (user != null) {

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), authorities
            );
        }

        Member member = memberService.queryByUserName(userName);
        if (member != null) {
            User memeberUser = new User();
            memeberUser.setUsername(member.getMemberCode());
            memeberUser.setPassword(new BCryptPasswordEncoder().encode(member.getCustPassword()));
            memeberUser.setEmail(member.getCustMail());
            memeberUser.setTelnumber(member.getCustTel());
            memeberUser.setOrgname(member.getMemName());
            memeberUser.setOrgid(member.getUniKey());
            userService.insertSelective(memeberUser);
            return new org.springframework.security.core.userdetails.User(
                    member.getMemberCode(), member.getCustPassword(), authorities
            );
        }
        throw new UsernameNotFoundException("User " + userName + " not found!");
    }
}
