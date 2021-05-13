package cn.qingmg.demoboot.config;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义 WebAuthenticationDetails
 *
 * @author 青木恭
 * @version 1.0
 * @date 2021-05-13
 */
public class MyWebAuthenticationDetails extends WebAuthenticationDetails {

    private boolean isVerify = false;

    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public MyWebAuthenticationDetails(HttpServletRequest request) {
        super(request);

        String code = request.getParameter("code");
        String sessionCode = request.getSession().getAttribute("code") + "";
        if (code != null && !"".equals(code)
                && sessionCode != null && !"".equals(sessionCode)
                && code.equals(sessionCode)) {
            isVerify = true;
        }
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }
}
