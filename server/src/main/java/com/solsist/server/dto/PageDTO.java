package com.solsist.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.alibaba.fastjson2.JSONObject;

@Data
@AllArgsConstructor
public class PageDTO {
    private int currentPage;
    private int pageSize;
    private JSONObject queryParams;
}
