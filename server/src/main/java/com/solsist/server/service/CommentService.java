package com.solsist.server.service;


import com.solsist.server.entity.CommentEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.solsist.server.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author solsist
 * @since 2024-01-01
 */
public interface CommentService extends IService<CommentEntity> {
    Double getAverageScore(Integer restaurantId);
}
