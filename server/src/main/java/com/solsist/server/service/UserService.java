package com.solsist.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.solsist.server.entity.UserEntity;

public interface UserService extends IService<UserEntity> {
    UserEntity login(String username, String password);
    boolean register(UserEntity userEntity);
}
