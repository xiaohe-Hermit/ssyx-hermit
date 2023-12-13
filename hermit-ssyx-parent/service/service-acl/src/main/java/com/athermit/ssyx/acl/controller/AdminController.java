package com.athermit.ssyx.acl.controller;

import com.athermit.ssyx.acl.service.AdminRoleService;
import com.athermit.ssyx.acl.service.AdminService;
import com.athermit.ssyx.acl.service.RoleService;
import com.athermit.ssyx.common.result.Result;
import com.athermit.ssyx.common.utils.MD5;
import com.athermit.ssyx.model.acl.Admin;
import com.athermit.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/admin/acl/user")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @ApiOperation("获取某个用户的所有角色")
    @GetMapping("toAssign/{adminId}")
    public Result getRoles(@PathVariable Long adminId){

        Map<String,Object> roleMap = roleService.getRoleByAdminId(adminId);
        return Result.ok(roleMap);
    }

    @ApiOperation("给某个用户分配角色")
    @PostMapping("doAssign")
    public Result assignRoles(@RequestParam Long adminId,@RequestParam Long[] roleId){
        roleService.saveAdminRole(adminId,roleId);
        return Result.ok(null);
    }


//    获取后台用户分页列表(带搜索)
    @ApiOperation("获取用户分页")
    @GetMapping("{page}/{limit}")
    public Result getPageList(@PathVariable Long page, @PathVariable Long limit, AdminQueryVo adminQueryVo){

        Page<Admin> pageParm = new Page<Admin>(page,limit);

        IPage<Admin> pageModel1 = adminService.selectAdminPage(pageParm,adminQueryVo);

        return Result.ok(pageModel1);

    }


//    根据ID获取某个后台用户
    @ApiOperation("根据id获取用户")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        Admin admin = adminService.getById(id);
        if (admin != null){
            return Result.ok(admin);
        }else {
            return Result.fail(admin);
        }
    }

//    保存一个新的后台用户
    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result add(@RequestBody Admin admin){
        admin.setPassword(MD5.encrypt(admin.getPassword()));
        boolean success = adminService.save(admin);
        if (success){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

//    更新一个后台用户
    @ApiOperation("更新用户")
    @PutMapping("update")
    public Result update(@RequestBody Admin admin){
        Admin originAdmin = adminService.getById(admin.getId());
        if (admin.getPassword()!=""){
            admin.setPassword(MD5.encrypt(admin.getPassword()));
        }else {
            admin.setPassword(originAdmin.getPassword());
        }
        boolean success = adminService.updateById(admin);
        if (success){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

//    删除某个用户
    @ApiOperation("删除用户")
    @DeleteMapping("remove/{id}")
    public Result deleteByid(@PathVariable Long id){
        boolean success = adminService.removeById(id);
        if (success){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

//    批量删除多个用户
    @ApiOperation("删除多个用户")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids){
        boolean success = adminService.removeByIds(ids);
        if (success){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

}
