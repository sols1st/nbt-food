package com.solsist.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
/**
 * <p>
 * 
 * </p>
 *
 * @author solsist
 * @since 2024-01-01
 */
@Getter
@Setter
@TableName("comment")
public class CommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "restaurant_id")
    private Integer restaurantId;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "content")
    private String content;

    @TableField(value = "score")
    private Integer score;

    @TableField(value = "gmt_create")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    @TableField(value = "gmt_modified")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtModified;


}
