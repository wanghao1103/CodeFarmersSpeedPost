package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.util.Screen;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanghao
 * @since 2020-03-05
 */
public interface IJobService extends IService<Job> {
    IPage<Job> queryJobListByQuery(Page<Job> iPage, String jname, String address);
    IPage<Job> queryJobList(Page<Job> iPage);
    IPage<Job> queryJobListScreen(Page<Job> iPage,String jname, String address, Screen screen);
    Job queryJobById(Long jid);


}
