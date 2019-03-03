package cn.ce.services.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * @author: ggs
 * @date: 2019-03-01 14:19
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
//                .and().formLogin()
//                .and().httpBasic()
                .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, @Qualifier("dataSource") DataSource dataSource) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder()).withUser("user1")
                .password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("SELECT userName,password,true FROM user " +
//                        "WHERE state != -1 AND userName = ?")
//                .authoritiesByUsernameQuery("SELECT userName,role_name FROM USER u " +
//                        "LEFT JOIN r_userrole m ON u.uid = m.uid " +
//                        "LEFT JOIN role r ON m.role_id = r.role_id " +
//                        "WHERE state != -1 AND u.userName = ?")
//                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
