package com.auto.shiro;

import com.auto.common.ResponseResult;
import com.auto.domain.User;
import com.auto.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //认证逻辑
        String username = authenticationToken.getPrincipal().toString();
        QueryWrapper queryCondition = new QueryWrapper();
        queryCondition.eq("username",username);
        User dbUser = userService.getOne(queryCondition);
        if(dbUser!=null){
/**            if(dbUser.getPassword().equals(authenticationToken.getCredentials())){
            }*/
            return new SimpleAuthenticationInfo(dbUser,dbUser.getPassword(),getName());
        }
/**      else if(dbUser.getUsername()==""){
            return (AuthenticationInfo) new ResponseResult("1","账号不能为空");
        }else if(dbUser.getPassword()==""){
            return (AuthenticationInfo) new ResponseResult("3","密码不能为空");
        } */

        return null;
    }

    //授权(权限管理)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
