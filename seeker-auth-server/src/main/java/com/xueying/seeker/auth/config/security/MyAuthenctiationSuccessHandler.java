package com.xueying.seeker.auth.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 自定义登录成功处理类
 * @Author zhangzhicheng
 * @Date 2019/09/10 23:32
 **/
@Component
public class MyAuthenctiationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        RequestCache cache = new HttpSessionRequestCache();
        SavedRequest savedRequest = cache.getRequest(request, response);
        savedRequest = null;
        // 如果来源请求为空则跳转到用户首页
        String url = "";
        if (savedRequest == null) {
            //url = "/blog/" + SecurityUtil.getLoginUser();
            url = "/uaa/loginSuccess";
        } else {
            url = savedRequest.getRedirectUrl();
        }
        response.sendRedirect(url);
    }
}
