package com.solsist.server.service.impl;


import com.solsist.server.entity.LocationEntity;
import com.solsist.server.mapper.LocationMapper;
import com.solsist.server.service.LocationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author solsist
 * @since 2024-01-01
 */
@Service
        public class LocationServiceImpl extends ServiceImpl<LocationMapper, LocationEntity> implements LocationService {
        }
