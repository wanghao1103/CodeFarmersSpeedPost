package com.example.service.impl;

import com.example.entity.Companyuser;
import com.example.mapper.CompanyuserMapper;
import com.example.service.ICompanyuserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
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
@Service
public class CompanyuserServiceImpl extends ServiceImpl<CompanyuserMapper, Companyuser> implements ICompanyuserService {
    @Resource
    private CompanyuserMapper companyuserMapper;
        public Companyuser Login(String cuser,String cpassword){
            return companyuserMapper.Login(cuser,cpassword);
        }
    @Override
    public int tian(String cuser, String cpassword) {
        return companyuserMapper.add(cuser,cpassword);
    }
}
