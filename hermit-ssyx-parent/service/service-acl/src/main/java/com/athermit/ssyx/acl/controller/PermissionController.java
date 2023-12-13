package com.athermit.ssyx.acl.controller;


import com.athermit.ssyx.acl.service.PermissionService;
import com.athermit.ssyx.common.result.Result;
import com.athermit.ssyx.model.acl.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/acl/permission")
@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //  获取权限(菜单/功能)列表
    @ApiOperation("获取权限(菜单/功能)列表")
    @GetMapping
    public Result getPermissionList(){
        List<Permission> list = permissionService.selectAllPermission();
        return Result.ok(list);
    }


//    保存一个权限项
    @ApiOperation("保存一个权限项")
    @PostMapping("save")
    public Result addPermission(@RequestBody Permission permission){
        boolean success = permissionService.save(permission);
        if (success){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

    //    更新一个权限项
    @ApiOperation("更新一个权限项")
    @PutMapping("update")
    public Result updatePermission(@RequestBody Permission permission){
        boolean success = permissionService.updateById(permission);
        if (success){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

    //    删除一个权限项
    @ApiOperation("删除一个权限项")
    @DeleteMapping("remove/{id}")
    public Result removePermission(@PathVariable Long id){
        permissionService.removeChildById(id);
        return Result.ok(null);
    }


//    查看某个角色的权限列表
    @ApiOperation("查看某个角色的权限列表")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId){

        List<Permission> result = permissionService.selectAllPermissionWithCheck(roleId);
        return Result.ok(result);
    }


//    给某个角色授权
    @ApiOperation("给某个角色授权")
    @PostMapping("doAssign")
    public Result doAssign(@RequestParam Long roleId,@RequestParam Long[] permissionId){

        permissionService.saveRolePermission(roleId,permissionId);
        return Result.ok(null);
    }


}
