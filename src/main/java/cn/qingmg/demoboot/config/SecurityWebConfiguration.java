package cn.qingmg.demoboot.config;

import cn.qingmg.demoboot.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

@Configuration
public class SecurityWebConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private VerifyCodeFilter verifyCodeFilter;
    @Autowired
    private UserLoginService userLoginService;

    private PasswordEncoder passwordEncoder() {
        DelegatingPasswordEncoder encoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        encoder.setDefaultPasswordEncoderForMatches(NoOpPasswordEncoder.getInstance());
        return encoder;
    }

    private MyAuthenticationProvider myAuthenticationProvider() {
        MyAuthenticationProvider provider = new MyAuthenticationProvider();
        provider.setUserDetailsService(userLoginService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(myAuthenticationProvider());
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userLoginService)
//                .passwordEncoder(passwordEncoder())
//        ;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置在校验账号密码前先校验验证码
        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .authorizeRequests()
                .antMatchers("/code", "/error").permitAll()
                .antMatchers("/test/111").hasRole("test1")
                .antMatchers("/test/222").hasRole("test2")
                .antMatchers("/test/333").hasAuthority("ROLE_test3")
                .antMatchers("/test/444").hasAuthority("ROLE_test4")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // 配置登陆页面，并放行
                .loginPage("/login").permitAll()
                // 配置登陆失败返回
                .failureHandler((req, resp, e) -> {
                    resp.setStatus(500);
                    resp.setContentType("text/html;charset=utf-8");
                    resp.getWriter().print(e.getMessage());
                })
                .and()
                .rememberMe()
//                    .rememberMeParameter("rme")
//                    .rememberMeCookieName("rmec")
                .tokenValiditySeconds(24 * 60 * 60)
//                    .tokenRepository(jdbcTokenRepository())
                .and()
                // 禁用 csrf
                .csrf().disable()
        ;
    }

    @Autowired
    private DataSource dataSource;

    private JdbcTokenRepositoryImpl jdbcTokenRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        repository.setCreateTableOnStartup(true);
        return repository;
    }
}