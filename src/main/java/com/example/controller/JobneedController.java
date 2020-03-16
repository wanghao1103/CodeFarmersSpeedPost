package com.example.controller;


import com.example.service.IJobneedService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanghao
 * @since 2020-03-05
 */
@RestController
@RequestMapping("/jobneed")
public class JobneedController {

    @Resource
    private IJobneedService iJobneedService;


}
