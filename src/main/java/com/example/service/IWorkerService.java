package com.example.service;

import com.example.entity.Worker;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanghao
 * @since 2020-03-05
 */
public interface IWorkerService extends IService<Worker> {
    Long queryWidByUserid(Long userid);
}
