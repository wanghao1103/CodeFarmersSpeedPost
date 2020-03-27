package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Job;
import com.example.mapper.JobMapper;
import com.example.service.IJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.util.Screen;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanghao
 * @since 2020-03-05
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

    @Resource
    private JobMapper jobMapper;

    @Override
    public IPage<Job> queryJobListByQuery(Page<Job> page, String jname, String address) {
        return jobMapper.getJobListByQuery(page,jname,address);
    }

    @Override
    public IPage<Job> queryJobList(Page<Job> page) {
        return jobMapper.getJobList(page);
    }


    @Override
    public IPage<Job> queryJobListScreen(Page<Job> page,String jname, String address, Screen screen) {
        return jobMapper.getJobListScreen(page,jname, address, screen);
    }

    @Override
    public Job queryJobById(Long jid) {
        return jobMapper.getJobById(jid);
    }


}
