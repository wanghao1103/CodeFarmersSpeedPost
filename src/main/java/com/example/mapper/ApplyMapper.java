package com.example.mapper;

import com.example.entity.Apply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanghao
 * @since 2020-03-05
 */
public interface ApplyMapper extends BaseMapper<Apply> {
    int addApply(@Param("wid") Long wid,@Param("jid") Long jid);
    int getApply(@Param("wid") Long wid,@Param("jid") Long jid);
}
