package com.athermit.ssyx.acl.service;

import com.athermit.ssyx.model.acl.Admin;
import com.athermit.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminService extends IService<Admin> {

    IPage selectAdminPage(Page<Admin> pageParm, AdminQueryVo adminQueryVo);
}
