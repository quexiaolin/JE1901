package com.ttp.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ttp.pojo.User;

@Controller
@RequestMapping("/userlogin")
public class UserController {
	
	@RequestMapping("/loginjsp")
	public String loginjsp(){
		return "user/login";
	}
	@RequestMapping("/dologin")
	public String dologin(User user,Map map){
		
        Subject currentUser = SecurityUtils.getSubject();
        // let's login the current user so we can check against roles and permissions:
        //isAuthenticated()判断是否登录过
        if (!currentUser.isAuthenticated()) {//未登录
            UsernamePasswordToken token = 
            		new UsernamePasswordToken(user.getName(),user.getPassword());//存储用户名和密码的对象
            token.setRememberMe(true);
            try {
                currentUser.login(token);//进行登录
            } 
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            	//登录失败
            	map.put("error", "账号或密码错误，请重新输入");
            	return "user/login";
            }
        }
        Session session = currentUser.getSession();
        session.setAttribute("loginuser", user.getName());
        return "main/main";
	}

}
