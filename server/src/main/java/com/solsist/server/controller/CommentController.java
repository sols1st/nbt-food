package com.solsist.server.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.solsist.server.dto.PageDTO;
import com.solsist.server.dto.ResponseDTO;
import com.solsist.server.entity.LocationEntity;
import com.solsist.server.entity.CommentEntity;
import com.solsist.server.service.LocationService;
import com.solsist.server.service.CommentService;
import com.solsist.server.util.PageUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/comment")
@RestController
public class CommentController {
    @Resource
    private CommentService commentService;
    @Resource
    private LocationService locationService;

    /**
     * 通过id查询单个评论的分数
     *
     * @return ResponseDTO
     */
    @GetMapping("queryScore/{id}")
    public ResponseDTO query(@PathVariable("id") Integer id) {
        Double averageScore = commentService.getAverageScore(id);
        if (averageScore != null) {
            return ResponseDTO.ok(averageScore);
        } else {
            return ResponseDTO.ok(0);
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
        Integer commentId = param.getIntValue("commentId");
        Integer restaurantId = param.getIntValue("restaurantId");


        IPage<CommentEntity> page = commentService.page(
                new Page<>(pageDTO.getCurrentPage(), pageDTO.getPageSize()),
                Wrappers.<CommentEntity>lambdaQuery()
                        .eq(restaurantId != 0, CommentEntity::getRestaurantId, restaurantId)
                        .eq(commentId != 0, CommentEntity::getId, commentId)
        );

        return PageUtil.pageResult(page);
    }

    /**
     * 添加餐馆
     *
     * @param commentEntity 餐馆实体
     * @return 添加结果
     */


    @PostMapping("add")
    public ResponseDTO add(@RequestBody CommentEntity commentEntity) {
        boolean save = commentService.save(commentEntity);
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
        boolean remove = commentService.removeById(id);
        if (remove) {
            return ResponseDTO.ok("删除成功");
        } else {
            return ResponseDTO.fail("删除失败");
        }
    }

    /**
     * 更新餐馆
     *
     * @param commentEntity 餐馆实体
     * @return 更新结果
     */
    @PutMapping("update")
    public ResponseDTO update(@RequestBody CommentEntity commentEntity) {
        boolean update = commentService.updateById(commentEntity);
        if (update) {
            return ResponseDTO.ok("更新成功");
        } else {
            return ResponseDTO.fail("更新失败");
        }
    }

}
