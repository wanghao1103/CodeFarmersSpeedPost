package com.example.service;

import com.example.entity.Jobneed;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanghao
 * @since 2020-03-05
 */
public interface IJobneedService extends IService<Jobneed> {

    Jobneed queryJobneedByJid(Long jid);
}
