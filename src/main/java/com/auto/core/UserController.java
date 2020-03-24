package com.auto.core;


import com.auto.common.ResponseResult;
import com.auto.domain.User;
import com.auto.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Anna.wang
 * @since 2020-03-05
 */
@RestController
@RequestMapping("/user")   //api
@Api("用户模块")
//@CrossOrigin 不好用,做一个统一的过滤器,让所有的类都可以用
@Configuration
public class UserController {
    /*
    spring ioc 容器==new
    @Autowired 依赖注入,即从容器中取出对象注入到属性中
     */
    @Autowired
    UserService userService;

    /**
     * 注册方法
     * @param user  用户
     * @return  接口响应结果
     */
    @PostMapping("/register")
    @ApiOperation(value = "注册方法",httpMethod = "POST")
    //TODO:使用validate进行后端注册参数校验---基本已完成,还差个重复的用户名没有处理
    public ResponseResult register(@RequestBody @Validated User user,BindingResult error){
        if(error.hasErrors()){
            String defaultMessage = error.getFieldError().getDefaultMessage();
            return new ResponseResult("1",defaultMessage);
        }else{
            //调用业务层的方法,插入新用户到数据库,处理异常
           // user.setRegtime(new Date());获取当前系统时间作为注册时间
            userService.save(user);
            return new ResponseResult("0",user.getUsername()+"注册成功");
        }

    }

    //账号验重
    @GetMapping("/find")
    @ApiOperation(value = "账号验重方法",httpMethod = "Get")
    public ResponseResult accountCheck(String username){
        //调用业务层方法,查询非主键列username是否存在
        QueryWrapper queryCondition = new QueryWrapper();
        queryCondition.eq("username",username);
        User user = userService.getOne(queryCondition);

        if(user==null){
            return new ResponseResult("0","验重通过!"); //已存在不允许注册
        }else{
            return new ResponseResult("1","账号已存在!"); //不存在则允许注册
        }
    }

/**
 * 登录接口参数的形式要用form,用json有问题
 */
    @PostMapping("/login")
    @ApiOperation(value = "登录方法",httpMethod = "POST")
    public ResponseResult login(User user){
            try {
                //通过user的用户名和密码得到一个token
                UsernamePasswordToken authcToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
                //通过shiro进行登录安全验证
                Subject subject = SecurityUtils.getSubject();  //得到当前操作对象
                subject.login(authcToken);
                String sessionId = (String) SecurityUtils.getSubject().getSession().getId();
                //登录成功的情况-->将sessionId返回给前端---data传userId,message里传sessionId
                User loginUser = (User)(subject.getPrincipal());
                return new ResponseResult("0",loginUser.getId() , sessionId);
            } catch (AuthenticationException e) {
                //处理登录失败的情况
                if (e instanceof UnknownAccountException) {
                    return new ResponseResult("2", "登录失败, 账号不正确!");
                } else {
                    return new ResponseResult("4", "登录失败, 密码不正确!");
                }
            }
    }

    @GetMapping("/logout")
    @ApiOperation(value = "账号退出方法",httpMethod = "Get")
    public ResponseResult logout() {
        //从shiro退出会话
        SecurityUtils.getSubject().logout();
        return new ResponseResult("1", "账号未登录!");
    }

    @GetMapping("/unauth")
    @ApiOperation(value = "未授权方法",httpMethod = "Get")
    public ResponseResult unauth() {
        return new ResponseResult("1", "账号未登录!");

    }
}

