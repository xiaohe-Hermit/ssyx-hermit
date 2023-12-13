package com.athermit.ssyx.acl.service.impl;

import com.athermit.ssyx.acl.mapper.PermissionMapper;
import com.athermit.ssyx.acl.service.PermissionService;
import com.athermit.ssyx.acl.service.RolePermissionService;
import com.athermit.ssyx.acl.utils.PermissionHelper;
import com.athermit.ssyx.model.acl.Permission;
import com.athermit.ssyx.model.acl.Role;
import com.athermit.ssyx.model.acl.RolePermission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public List<Permission> selectAllPermission() {
        List<Permission> permissionsList = baseMapper.selectList(null);

        List<Permission> result = PermissionHelper.buildPermission(permissionsList);

        return result;
    }

    @Override
    public void removeChildById(Long id) {

        List<Long> idList = new ArrayList<>();

        this.getAllPermissionId(id, idList);

        idList.add(id);
        //批量删除
        baseMapper.deleteBatchIds(idList);

    }

    @Override
    public List<Permission> selectAllPermissionWithCheck(Long roleId) {
        //查出所有菜单
        List<Permission> permissionsList = baseMapper.selectList(null);
        //用条件找出该角色相关的菜单
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId, roleId);
        List<RolePermission> rolePermissionList = rolePermissionService.list(wrapper);
        List<Long> permissionIdList = rolePermissionList.stream().map(item -> item.getPermissionId()).collect(Collectors.toList());

        //将相关的菜单设置为selected
        for (Permission it : permissionsList) {
            if (permissionIdList.contains(it.getId())) {
                it.setSelect(true);
            }
        }

        //按照标准格式输出
        List<Permission> result = PermissionHelper.buildPermission(permissionsList);

        return result;
    }

    @Override
    public void saveRolePermission(Long roleId, Long[] permissionIds) {
        //删除已分配的菜单角色记录
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId, roleId);
        rolePermissionService.remove(wrapper);

        List<RolePermission> rolePermissionList = new ArrayList<>();
        //将选中的菜单再次选中
        for (Long permissionId : permissionIds) {
            //创建封装类
            RolePermission rolePermission = new RolePermission();
            //开始封装
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            //放入集合中
            rolePermissionList.add(rolePermission);
        }

        rolePermissionService.saveBatch(rolePermissionList);

    }

    private void getAllPermissionId(Long id, List<Long> idList) {

        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getPid, id);
        List<Permission> childList = baseMapper.selectList(wrapper);

        //递归查找树状结构
        childList.stream().forEach(item -> {
            idList.add(item.getId());
            this.getAllPermissionId(item.getId(), idList);
        });

    }
}
