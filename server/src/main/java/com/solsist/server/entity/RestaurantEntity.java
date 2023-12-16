package com.solsist.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("restaurant")
public class RestaurantEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "pic")
    private String pic;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "description")
    private String description;

    @TableField(value = "location_id")
    private Integer locationId;

    @TableField(value = "location_name")
    private String locationName;

}
