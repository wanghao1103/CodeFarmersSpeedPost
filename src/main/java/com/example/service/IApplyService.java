package com.example.service;

import com.example.entity.Apply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanghao
 * @since 2020-03-05
 */
public interface IApplyService extends IService<Apply> {
    int insertApply(Long wid, Long jid);
    int queryApply(Long wid, Long jid);
}
