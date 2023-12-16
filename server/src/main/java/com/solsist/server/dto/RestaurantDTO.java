package com.solsist.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

    private Integer id;

    private String name;

    private String pic;

    private Integer userId;

    private String description;

    private Integer locationId;

    private String locationName;
}
