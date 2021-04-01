package cn.qingmg.demoboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityWebConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 存储在内存中
                .inMemoryAuthentication()
                // 创建用户 user1
                .withUser("user1").password("{noop}123").authorities("admin")
                .and()
                // 创建用户 user2
                .withUser("user2").password("{bcrypt}" + new BCryptPasswordEncoder().encode("123")).authorities("admin");
    }
}
