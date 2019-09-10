/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: indexController
 * Author:   zhangzhicheng
 * Date:     2019/09/10
 * Description: 登录成功跳转页面
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录成功携参跳转页面
 *
 * @author zhangzhicheng
 * @date 2019-09-10
 */
@Controller
public class indexController {
    @RequestMapping(value = "/loginSuccess")
    public String  index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //主体名，即登录用户名
        String name = auth.getName();
        model.addAttribute("username", name);
        return "index";
    }
}
