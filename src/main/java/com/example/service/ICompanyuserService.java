package com.example.service;

import com.example.entity.Companyuser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanghao
 * @since 2020-03-04
 */
public interface ICompanyuserService extends IService<Companyuser> {
    Companyuser Login(String cuser, String cpassword);
    int tian(String cuser, String cpassword);
}
