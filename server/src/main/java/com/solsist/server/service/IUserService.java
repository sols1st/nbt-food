package com.solsist.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.solsist.server.entity.User;

public interface IUserService extends IService<User> {
    User login(String username, String password);
    boolean register(User user);
}
