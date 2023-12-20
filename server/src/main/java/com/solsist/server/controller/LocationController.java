package com.solsist.server.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.solsist.server.dto.PageDTO;
import com.solsist.server.dto.ResponseDTO;
import com.solsist.server.entity.LocationEntity;
import com.solsist.server.entity.RestaurantEntity;
import com.solsist.server.service.LocationService;
import com.solsist.server.util.PageUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/location")
@RestController
public class LocationController {

    @Resource
    private LocationService locationService;

    /**
     * 获取所有location
     *
     * @return ResponseDTO
     */
    @GetMapping("list")
    public ResponseDTO list() {
        List<LocationEntity> list = locationService.list();
        return ResponseDTO.ok(list);
    }

    /**
     * 分页查询地点
     *
     * @param pageDTO 分页参数
     * @return 分页结果
     */
    @RequestMapping("page")
    public ResponseDTO page(@RequestBody PageDTO pageDTO) {
        JSONObject param = pageDTO.getQueryParams();
        String name = param.getString("name");
        Integer locationId = param.getIntValue("location_id");


        IPage<LocationEntity> page = locationService.page(
                new Page<>(pageDTO.getCurrentPage(), pageDTO.getPageSize()),
                Wrappers.<LocationEntity>lambdaQuery()
                        .like(name != null, LocationEntity::getName, name)
                        .eq(locationId != 0, LocationEntity::getId, locationId)
        );

        return PageUtil.pageResult(page);
    }

    /**
     * 添加餐馆
     *
     * @param locationEntity 地点实体
     * @return 添加结果
     */
    @PostMapping("add")
    public ResponseDTO add(@RequestBody LocationEntity locationEntity) {
        boolean save = locationService.save(locationEntity);
        if (save) {
            return ResponseDTO.ok("添加成功");
        } else {
            return ResponseDTO.fail("添加失败");
        }
    }

    /**
     * 删除餐馆
     *
     * @param id 地点id
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    public ResponseDTO delete(@PathVariable("id") Integer id) {
        boolean remove = locationService.removeById(id);
        if (remove) {
            return ResponseDTO.ok("删除成功");
        } else {
            return ResponseDTO.fail("删除失败");
        }
    }

    /**
     * 更新餐馆
     *
     * @param locationEntity 地点实体
     * @return 更新结果
     */
    @PutMapping("update")
    public ResponseDTO update(@RequestBody LocationEntity locationEntity) {
        boolean update = locationService.updateById(locationEntity);
        if (update) {
            return ResponseDTO.ok("更新成功");
        } else {
            return ResponseDTO.fail("更新失败");
        }
    }

}
