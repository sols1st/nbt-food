package com.solsist.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.solsist.server.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
