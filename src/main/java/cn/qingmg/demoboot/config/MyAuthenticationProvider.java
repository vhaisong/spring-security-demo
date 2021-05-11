package cn.qingmg.demoboot.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class MyAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        HttpServletRequest req = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        if ("POST".equalsIgnoreCase(req.getMethod())) {
            String code = req.getParameter("code");
            String sessionCode = req.getSession().getAttribute("code") + "";
            if (code == null || "".equals(code)) {
                throw new AuthenticationServiceException("验证码不能为空");
            } else if (!code.equals(sessionCode)) {
                throw new AuthenticationServiceException("验证码错误");
            }
        }
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}