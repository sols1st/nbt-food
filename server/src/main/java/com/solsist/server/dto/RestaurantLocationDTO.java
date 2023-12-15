package com.solsist.server.dto;

import com.solsist.server.entity.LocationEntity;
import com.solsist.server.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestaurantLocationDTO {
    private LocationEntity location;
    private List<RestaurantEntity> restaurantList;
}
