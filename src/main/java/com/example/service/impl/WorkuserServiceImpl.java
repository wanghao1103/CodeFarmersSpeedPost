package com.example.service.impl;

import com.example.entity.Workuser;
import com.example.mapper.WorkuserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.service.IWorkuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanghao
 * @since 2020-03-04
 */
@Service("workuserservice")
public class WorkuserServiceImpl extends ServiceImpl<WorkuserMapper, Workuser> implements IWorkuserService {
        @Autowired
        private WorkuserMapper workuserMapper;
        public Workuser Login(String wuser,String wpassword){
            return workuserMapper.Login(wuser,wpassword);
        }
     @Override
     public int add(String wuser, String wpassword, String wphone, String wemail) {
        return workuserMapper.insert(wuser,wpassword,wphone,wemail);
    }

}
