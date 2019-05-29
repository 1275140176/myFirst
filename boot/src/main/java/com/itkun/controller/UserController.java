package com.itkun.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("UserCoroller.hello()");
        return "hello";
    }
    @RequestMapping("testThymeleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("name","郭坤");
        return "test";
    }

    @RequestMapping("/add")
    public String addUser(){
        return "/user/add";
    }
    @RequestMapping("/unAuth")
    public String unAuth(){
        return "/unAuth";
    }
    @RequestMapping("/update")
    public String updateUser(){
        return "/user/update";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login";
    }

    @RequestMapping("/login")
    public String login(String name,String password,Model model){
        //获取subject
        Subject subject= SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token=new UsernamePasswordToken(name,password);

        try {
            subject.login(token);
            return "redirect:/testThymeleaf";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

}
