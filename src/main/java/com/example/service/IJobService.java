package com.example.service;

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
    List<Job> queryJobListByQuery(String jname,String address);
    List<Job> queryJobList();
    List<Job> queryJobListScreen(String jname, String address, Screen screen);
    Job queryJobById(Long jid);


}
