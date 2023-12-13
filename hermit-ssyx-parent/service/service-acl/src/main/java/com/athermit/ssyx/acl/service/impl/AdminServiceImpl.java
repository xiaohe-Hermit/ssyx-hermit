package com.athermit.ssyx.acl.service.impl;

import com.athermit.ssyx.acl.mapper.AdminMapper;
import com.athermit.ssyx.acl.service.AdminService;
import com.athermit.ssyx.model.acl.Admin;
import com.athermit.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements AdminService {
    @Override
    public IPage<Admin> selectAdminPage(Page<Admin> pageParm, AdminQueryVo adminQueryVo) {

        String name = adminQueryVo.getName();
        String username = adminQueryVo.getUsername();

        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();

        if (!StringUtils.isEmpty(name)){
            wrapper.like(Admin::getName,name);
        }
        if (!StringUtils.isEmpty(username)){
            wrapper.like(Admin::getUsername,username);
        }

        IPage<Admin> adminPage = baseMapper.selectPage(pageParm,wrapper);

        return adminPage;
    }
}
