package com.example.mapper;

import com.example.entity.Company;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanghao
 * @since 2020-03-05
 */
public interface CompanyMapper extends BaseMapper<Company> {
    Company getCompanyByCid(Long cid);
}
