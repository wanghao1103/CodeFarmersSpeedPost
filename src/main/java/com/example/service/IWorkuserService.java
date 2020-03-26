package com.example.service;

import com.example.entity.Workuser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanghao
 * @since 2020-03-04
 */
public interface IWorkuserService extends IService<Workuser> {
        Workuser Login(String wuser, String wpassword);
        int add(String wuser,String wpassword,String wphone,String wemail);
}
