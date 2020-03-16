package com.example.service.impl;

import com.example.entity.Jobneed;
import com.example.mapper.JobneedMapper;
import com.example.service.IJobneedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanghao
 * @since 2020-03-05
 */
@Service
public class JobneedServiceImpl extends ServiceImpl<JobneedMapper, Jobneed> implements IJobneedService {

    @Resource
    private JobneedMapper jobneedMapper;


    @Override
    public Jobneed queryJobneedByJid(Long jid) {
        return jobneedMapper.getJobneedByJid(jid);
    }
}
