package com.example.service.impl;

import com.example.entity.Apply;
import com.example.mapper.ApplyMapper;
import com.example.service.IApplyService;
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
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements IApplyService {

    @Resource
    private ApplyMapper applyMapper;


    @Override
    public int insertApply(Long wid, Long jid) {
        return applyMapper.addApply(wid,jid);
    }

    @Override
    public int queryApply(Long wid, Long jid) {
        return applyMapper.getApply(wid, jid);
    }
}
