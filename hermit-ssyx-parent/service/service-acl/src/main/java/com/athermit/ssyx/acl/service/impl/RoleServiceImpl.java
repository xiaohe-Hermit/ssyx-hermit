package com.athermit.ssyx.acl.service.impl;

import com.athermit.ssyx.acl.mapper.RoleMapper;
import com.athermit.ssyx.acl.service.AdminRoleService;
import com.athermit.ssyx.acl.service.RoleService;
import com.athermit.ssyx.model.acl.AdminRole;
import com.athermit.ssyx.model.acl.Role;
import com.athermit.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private AdminRoleService adminRoleService;

    @Override
    public IPage<Role> selectRolePage(Page<Role> pageParm, RoleQueryVo roleQueryVo) {

        String roleName = roleQueryVo.getRoleName();

        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();

        if (!StringUtils.isEmpty(roleName)) {
            wrapper.like(Role::getRoleName, roleName);
        }

        IPage<Role> rolePage = baseMapper.selectPage(pageParm, wrapper);

        return rolePage;
    }

    @Override
    public Map<String, Object> getRoleByAdminId(Long adminId) {

        List<Role> allRoleList = baseMapper.selectList(null);

        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(AdminRole::getAdminId, adminId);
        List<AdminRole> adminRoleList = adminRoleService.list(wrapper);
        List<Long> roleIdList = adminRoleList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());

        List<Role> assignRoleList = new ArrayList<>();

        for (Role role : allRoleList) {
            if (roleIdList.contains(role.getId())) {
                assignRoleList.add(role);
            }
        }

        //放入数据
        Map<String, Object> result = new HashMap<>();

        result.put("allRolesList", allRoleList);
        result.put("assignRoles", assignRoleList);
        return result;
    }

    @Override
    public void saveAdminRole(Long adminId, Long[] roleIds) {
        //删除已分配过的角色,防止出现重复信息
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId, adminId);
        adminRoleService.remove(wrapper);

        List<AdminRole> adminRoleList = new ArrayList<>();
        //重新分配
        for (Long roleId : roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setRoleId(roleId);
            adminRole.setAdminId(adminId);
            adminRoleList.add(adminRole);
        }
        adminRoleService.saveBatch(adminRoleList);


    }
}
