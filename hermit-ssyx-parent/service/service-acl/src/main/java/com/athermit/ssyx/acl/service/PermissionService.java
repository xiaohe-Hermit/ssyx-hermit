package com.athermit.ssyx.acl.service;

import com.athermit.ssyx.model.acl.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PermissionService extends IService<Permission> {


    List<Permission> selectAllPermission();

    void removeChildById(Long id);

    List<Permission> selectAllPermissionWithCheck(Long roleId);

    void saveRolePermission(Long roleId, Long[] permissionIds);
}
