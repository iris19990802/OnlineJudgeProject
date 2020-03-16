package com.iris.java.onlinejudge.web.service;

import com.iris.java.onlinejudge.web.mapper.normal.UserMapper;
import com.iris.java.onlinejudge.web.pojo.bo.RegisterBO;
import com.iris.java.onlinejudge.web.pojo.db.User;
import com.iris.java.onlinejudge.web.utils.MD5Util;
import com.iris.java.onlinejudge.web.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
        public User addUser(RegisterBO registerBO) {

        User newUser = new User();
        newUser.setUserId(UUIDUtil.getUUID());
        newUser.setUserName(registerBO.getUserName());
        newUser.setUserEmail(registerBO.getUserEmail());
        newUser.setUserPasssord(MD5Util.getMD5(registerBO.getUserPasswordMd5_1()));
        newUser.setUserRegisterDate(new Date());

        userMapper.insert(newUser);

        return newUser;
    }
}
