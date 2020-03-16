package com.iris.java.onlinejudge.web.controller.normal;


import com.iris.java.onlinejudge.web.pojo.bo.RegisterBO;
import com.iris.java.onlinejudge.web.pojo.db.User;
import com.iris.java.onlinejudge.web.service.UserService;
import com.iris.java.onlinejudge.web.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(value="注册登陆",tags={"注册登陆的相关接口"})
@RequestMapping("/api/login")
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/register")
    public JSONResult register(@RequestBody @Valid RegisterBO registerBO,
                               HttpServletRequest request,
                               HttpServletResponse response){

        // 参数校验已在JSR中实现

        User newUser = userService.addUser(registerBO);

        // 去掉敏感信息
        User finalUser = setNullProperty(newUser);

        // 往用户端打Cookie
        CookieUtil.setCookie();

        return JSONResult.ok(newUser);

    }

    @RequestMapping("/login")
    public JSONResult login(@RequestBody String userName ,@RequestBody String password_md5){

        // 参数名称校验
        if (StringUtils.isBlank(userName) ||
                StringUtils.isBlank(password_md5)) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }

        // 实现登陆
        userService.userLogin

    }

    private User setNullProperty(User newUser){
        newUser.setUserEmail(null);
        newUser.setUserPasssord(null);
        newUser.setUserRegisterDate(null);

        return newUser;
    }
}
