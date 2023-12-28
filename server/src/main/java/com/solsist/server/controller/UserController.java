package com.solsist.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.solsist.server.dto.PageDTO;
import com.solsist.server.dto.ResponseDTO;
import com.solsist.server.entity.UserEntity;
import com.solsist.server.service.UserService;
import com.solsist.server.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    @GetMapping("list")
    public List<UserEntity> list() {
        List<UserEntity> list = userService.list();
        return list;
    }

    /**
     * 分页查询用户
     *
     * @param pageDTO 分页参数
     * @return 分页结果
     */
    @GetMapping("page")
    public ResponseDTO page(@RequestBody PageDTO pageDTO) {
        JSONObject param = pageDTO.getQueryParams();
        int id = param.getIntValue("id");
        String username = param.getString("username");

        IPage<UserEntity> page = userService.page(
                new Page<>(pageDTO.getCurrentPage(), pageDTO.getPageSize()),
                Wrappers.<UserEntity>lambdaQuery()
                        .like(username != null, UserEntity::getUsername, username)
                        .eq(id != 0, UserEntity::getId, id)
                        .orderByDesc(UserEntity::getId)
        );

        return PageUtil.pageResult(page);
    }

    @PostMapping("/login")
    public UserEntity login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        return userService.login(username, password);
    }


    @PostMapping("/register")
    public boolean register(@RequestBody UserEntity userEntity) {
        return userService.register(userEntity);
    }


    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDTO delete(@PathVariable("id") int id) {
        boolean remove = userService.removeById(id);
        if (remove) {
            return ResponseDTO.ok("删除成功");
        } else {
            return ResponseDTO.fail("删除失败");
        }
    }

    /**
     * 更新用户
     *
     * @param userEntity 用户实体
     * @return 更新结果
     */
    @PutMapping("/update")
    public ResponseDTO update(@RequestBody UserEntity userEntity) {
        boolean update = userService.updateById(userEntity);
        if (update) {
            return ResponseDTO.ok("更新成功");
        } else {
            return ResponseDTO.fail("更新失败");
        }
    }

    @PostMapping("/add")
    public ResponseDTO add(@RequestBody UserEntity userEntity) {
        boolean save = userService.save(userEntity);
        if (save) {
            return ResponseDTO.ok("添加成功");
        } else {
            return ResponseDTO.fail("添加失败");
        }
    }


}
