package com.example.service;

import com.example.entity.Job;
import com.baomidou.mybatisplus.extension.service.IService;

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
    List<Job> queryJobListScreen(String jname,String address,int day,float min,
                                 float max,int natureid, int minyears,int maxyears,String education,int minscale,int maxscale);
    Job queryJobById(Long jid);


}
