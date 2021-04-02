package cn.qingmg.demoboot.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 *
 * @author 青木恭
 * @version 1.0
 * @date 2021-04-01
 */
@Component
public class VerifyCodeFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if ("POST".equalsIgnoreCase(req.getMethod())) {
            System.out.println(req.getServletPath());
            String code = req.getParameter("code");
            String sessionCode = req.getSession().getAttribute("code") + "";
            if (code == null || "".equals(code)) {
                resp.setStatus(500);
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().print("验证码不能为空");
                return;
            } else if (!code.equals(sessionCode)) {
                resp.setStatus(500);
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().print("验证码错误");
                return;
            }
        }
        chain.doFilter(req, resp);
    }
}
