package com.solsist.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.solsist.server.dto.PageDTO;
import com.solsist.server.dto.ResponseDTO;
import com.solsist.server.entity.User;
import com.solsist.server.service.IUserService;
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
    private IUserService userService;

    @GetMapping("list")
    public List<User> getUserList() {
        List<User> list = userService.list();
        return list;
    }

    // 分页查询
    @RequestMapping("page")
    public ResponseDTO getUserPage(@RequestBody PageDTO pageDTO) {
        JSONObject param = pageDTO.getQueryParams();
        int id = param.getIntValue("id");
        String username = param.getString("username");
        System.out.println(username);
        System.out.println(id);

        IPage<User> page = userService.page(
                new Page<>(pageDTO.getCurrentPage(), pageDTO.getPageSize()),
                Wrappers.<User>lambdaQuery()
                        .like(username != null, User::getUsername, username)
                        .eq(id != 0, User::getId, id)
                        .orderByDesc(User::getId)
        );
        return ResponseDTO.ok(page.getRecords());
    }

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> request) {
        log.info("login: {}", request);
        String username = request.get("username");
        String password = request.get("password");
        return userService.login(username, password);
    }

    @PostMapping("/register")
    public boolean register(@RequestBody User user) {
        return userService.register(user);
    }
}
