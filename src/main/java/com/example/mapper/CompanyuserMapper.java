package com.example.mapper;

import com.example.entity.Companyuser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Workuser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanghao
 * @since 2020-03-04
 */
public interface CompanyuserMapper extends BaseMapper<Companyuser> {
    Companyuser Login(@Param("cuser") String cuser, @Param("cpassword") String cpassword);
    int add(@Param("cuser") String cuser,@Param("cpassword") String cpassword);
}
