package com.example.demo.config;

import com.example.demo.domain.User;
import com.example.demo.service.impl.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {


    @Autowired
    UserServiceImpl userService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权执行了");
        return null;
    }


    //用户认证模块
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证执行了");
        //前端获取的数据
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //真实数据
        User user = userService.queryUserByName(userToken.getUsername());
        if (user == null){
            return null;    //抛出异常UnknownAccountException
        }
        //密码验证 shiro自己做
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}
