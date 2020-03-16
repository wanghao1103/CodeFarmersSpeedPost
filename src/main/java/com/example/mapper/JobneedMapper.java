package com.example.mapper;

import com.example.entity.Jobneed;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanghao
 * @since 2020-03-05
 */
public interface JobneedMapper extends BaseMapper<Jobneed> {
        Jobneed getJobneedByJid(Long jid);
}
