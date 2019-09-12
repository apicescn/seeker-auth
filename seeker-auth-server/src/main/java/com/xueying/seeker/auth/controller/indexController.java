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

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 登录成功携参跳转页面
 *
 * @author zhangzhicheng
 * @date 2019-09-10
 */
@Controller
public class indexController {
    @RequestMapping(value = "/loginSuccess")
    public String  index(Model model,  Principal user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(user);
        //主体名，即登录用户名
        String name = auth.getName();
        model.addAttribute("username", name);
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm");
        model.addAttribute("loginTime", dateFormat.format(calendar.getTime()));
        return "index";
    }
    //用户登录信息
    @RequestMapping(value = "user/userInfo")
    public String userLoginInfo(){
        return "user/user_info";
    }
    //用户配置详细信息
    @RequestMapping(value = "user/userDetails")
    public String userDetails(){
        return "user/user_details";
    }

    /**
     * 客户端管理页面跳转
     * @return
     */
    @RequestMapping(value = "client/clientDetails")
    public String clientDetails(){
        return "client/client_details";
    }

    /**
     * 客户端添加
     * @param model
     * @return
     */
    @RequestMapping("client/add")
    public  String addClient(Model model){
        return "client/add";
    }
}
