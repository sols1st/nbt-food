package com.solsist.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.solsist.server.dto.PageDTO;
import com.solsist.server.dto.ResponseDTO;
import com.solsist.server.entity.RestaurantEntity;
import com.solsist.server.service.RestaurantService;
import com.solsist.server.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RequestMapping("/restaurant")
@RestController
@Slf4j
public class RestaurantController {
    @Resource
    private RestaurantService restaurantService;

    /**
     * 查询所有餐馆
     *
     * @return 餐馆列表
     */
    @GetMapping("list")
    public List<RestaurantEntity> list() {
        List<RestaurantEntity> list = restaurantService.list();
        return list;
    }

    /**
     * 分页查询餐馆
     *
     * @param pageDTO 分页参数
     * @return 分页结果
     */
    @RequestMapping("page")
    public ResponseDTO page(@RequestBody PageDTO pageDTO) {
        JSONObject param = pageDTO.getQueryParams();
        String name = param.getString("name");
        Integer location_id = param.getIntValue("restaurant_id");

        IPage<RestaurantEntity> page = restaurantService.page(
                new Page<>(pageDTO.getCurrentPage(), pageDTO.getPageSize()),
                Wrappers.<RestaurantEntity>lambdaQuery()
                        .like(name != null, RestaurantEntity::getName, name)
                        .eq(location_id != 0, RestaurantEntity::getLocationId, location_id)
        );

        return PageUtil.pageResult(page);
    }

}
