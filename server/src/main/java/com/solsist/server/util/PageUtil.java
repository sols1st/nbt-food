package com.solsist.server.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.solsist.server.dto.ResponseDTO;
import com.solsist.server.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


public class PageUtil {
    @Data
    @AllArgsConstructor
    public static class PageResultDTO<T> {
        private List<T> records;
        private long totalRecords;
        private long totalPages;
    }

    public static <T> ResponseDTO pageResult(IPage page) {
        PageResultDTO<UserEntity> pageResult = new PageResultDTO<>(
                page.getRecords(),
                page.getTotal(),
                page.getPages()
        );
        return ResponseDTO.ok(pageResult);
    }
}
