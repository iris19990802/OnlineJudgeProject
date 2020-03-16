package com.iris.java.onlinejudge.web.service;

import com.iris.java.onlinejudge.web.pojo.bo.RegisterBO;
import com.iris.java.onlinejudge.web.pojo.db.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User addUser(RegisterBO registerBO);

}
