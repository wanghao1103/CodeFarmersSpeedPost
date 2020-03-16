package com.example.mapper;

import com.example.entity.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanghao
 * @since 2020-03-05
 */
public interface JobMapper extends BaseMapper<Job> {
    List<Job> getJobListByQuery(String jname,String address);
    List<Job> getJobList();
    List<Job> getJobListScreen(@Param("jname") String jname,@Param("address") String address,
                              @Param("day") int day,@Param("min")float min,@Param("max") float max,
                              @Param("natureid") int natureid,@Param("minyears") int minyears,@Param("maxyears")int maxyears,
                              @Param("education") String education,@Param("minscale") int minscale,@Param("maxscale")int maxscale);
    Job getJobById(@Param("jid")Long jid);
}
