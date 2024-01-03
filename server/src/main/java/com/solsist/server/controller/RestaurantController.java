package com.solsist.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.solsist.server.dto.PageDTO;
import com.solsist.server.dto.ResponseDTO;
import com.solsist.server.dto.RestaurantLocationDTO;
import com.solsist.server.entity.LocationEntity;
import com.solsist.server.entity.RestaurantEntity;
import com.solsist.server.service.LocationService;
import com.solsist.server.service.RestaurantService;
import com.solsist.server.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson2.JSONObject;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequestMapping("/restaurant")
@RestController
@Slf4j
public class RestaurantController {
    @Resource
    private RestaurantService restaurantService;
    @Resource
    private LocationService locationService;

    /**
     * 查询所有餐馆以地点分类
     *
     * @return ResponseDTO
     */



    @GetMapping("list")
    public ResponseDTO list() {
        List<RestaurantEntity> list = restaurantService.list();
        Map<Integer, List<RestaurantEntity>> map = list.stream().collect(Collectors.groupingBy(RestaurantEntity::getLocationId));
        List<LocationEntity> locationList = locationService.list();
        List<RestaurantLocationDTO> result = locationList.stream().map(locationEntity -> {
            RestaurantLocationDTO restaurantLocationDTO = new RestaurantLocationDTO();
            restaurantLocationDTO.setLocation(locationEntity);
            restaurantLocationDTO.setRestaurantList(map.get(locationEntity.getId()));
            return restaurantLocationDTO;
        }).collect(Collectors.toList());
        return ResponseDTO.ok(result);
    }

    /**
     * 随机获取一个餐馆
     *
     * @return ResponseDTO
     */
    @GetMapping("random")
    public ResponseDTO random() {
        List<RestaurantEntity> list = restaurantService.list();
        int index = (int) (Math.random() * list.size());
        return ResponseDTO.ok(list.get(index));
    }

    /**
     * 通过id查询单个餐馆
     *
     * @return ResponseDTO
     */
    @GetMapping("query/{id}")
    public ResponseDTO query(@PathVariable("id") Integer id) {
        RestaurantEntity restaurant = restaurantService.getById(id);
        if (restaurant != null) {
            return ResponseDTO.ok(restaurant);
        } else {
            return ResponseDTO.fail(404,"没有找到对应餐馆");
        }

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
        Integer restaurantId = param.getIntValue("restaurantId");
        Integer locationId = param.getIntValue("locationId");



        IPage<RestaurantEntity> page = restaurantService.page(
                new Page<>(pageDTO.getCurrentPage(), pageDTO.getPageSize()),
                Wrappers.<RestaurantEntity>lambdaQuery()
                        .like(name != null, RestaurantEntity::getName, name)
                        .eq(locationId  != 0, RestaurantEntity::getLocationId, locationId)
                        .eq(restaurantId  != 0, RestaurantEntity::getId, restaurantId)
        );

        return PageUtil.pageResult(page);
    }

    /**
     * 添加餐馆
     *
     * @param restaurantEntity 餐馆实体
     * @return 添加结果
     */


    @PostMapping("add")
    public ResponseDTO add(@RequestBody RestaurantEntity restaurantEntity) {
        boolean save = restaurantService.save(restaurantEntity);
        if (save) {
            return ResponseDTO.ok("添加成功");
        } else {
            return ResponseDTO.fail("添加失败");
        }
    }

    /**
     * 删除餐馆
     *
     * @param id 餐馆id
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    public ResponseDTO delete(@PathVariable("id") Integer id) {
        boolean remove = restaurantService.removeById(id);
        if (remove) {
            return ResponseDTO.ok("删除成功");
        } else {
            return ResponseDTO.fail("删除失败");
        }
    }

    /**
     * 更新餐馆
     *
     * @param restaurantEntity 餐馆实体
     * @return 更新结果
     */
    @PutMapping("update")
    public ResponseDTO update(@RequestBody RestaurantEntity restaurantEntity) {
        boolean update = restaurantService.updateById(restaurantEntity);
        if (update) {
            return ResponseDTO.ok("更新成功");
        } else {
            return ResponseDTO.fail("更新失败");
        }
    }

}
