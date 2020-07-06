package com.example.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/cfg")
public class LoginController {
    @RequestMapping("/index")
    public String toIndex(Model model){
        model.addAttribute("msg", "hello,Shiro");
        System.out.println("我被执行了");
        return "index";
    }

    @RequestMapping("/add")
    public String add(){
        return "add";
    }

    @RequestMapping("/update")
    public String update(){
        return "update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        System.out.println("权限不足去登入");
        return "login";
    }


    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        System.out.println("我被执行了");
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        System.out.println("-------");
        //封装用户的登入数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            subject.login(token);           //对应执行UserRealm里面的方法
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg", "用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
}
