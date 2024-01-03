package com.solsist.server.mapper;

import com.solsist.server.entity.CommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author solsist
 * @since 2024-01-01
 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {
    @Select("SELECT AVG(score) FROM comment WHERE restaurant_id = #{restaurantId}")
    Double getAverageScore(@Param("restaurantId") Integer restaurantId);
}
