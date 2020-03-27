package com.example.service.impl;

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
    public List<Job> queryJobListByQuery(String jname, String address) {
        return jobMapper.getJobListByQuery(jname,address);
    }

    @Override
    public List<Job> queryJobList() {
        return jobMapper.getJobList();
    }


    @Override
    public List<Job> queryJobListScreen(String jname, String address, Screen screen) {
        return jobMapper.getJobListScreen(jname, address, screen);
    }

    @Override
    public Job queryJobById(Long jid) {
        return jobMapper.getJobById(jid);
    }


}
