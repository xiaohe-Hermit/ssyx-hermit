package com.athermit.ssyx.acl.controller;


import com.athermit.ssyx.acl.service.RoleService;
import com.athermit.ssyx.common.result.Result;
import com.athermit.ssyx.model.acl.Role;
import com.athermit.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/acl/role")
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;


    @ApiOperation("角色条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result getPageList(@PathVariable Long page, @PathVariable Long limit, RoleQueryVo roleQueryVo) {

        Page<Role> pageParm = new Page<>(page, limit);

        IPage<Role> pageModel1 = roleService.selectRolePage(pageParm, roleQueryVo);

        return Result.ok(pageModel1);
    }

    @ApiOperation("添加一个新角色")
    @PostMapping("save")
    public Result save(@RequestBody Role role) {
        boolean isSuccess = roleService.save(role);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }

    }

    @ApiOperation("获取某个角色")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        Role role = roleService.getById(id);
        if (role != null){
            return Result.ok(role);
        }else {
            return Result.fail(role);
        }
    }

    @ApiOperation("更新一个角色")
    @PutMapping("update")
    public Result updateById(@RequestBody Role role){
        boolean isSuccess = roleService.updateById(role);
        if (isSuccess){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

    @ApiOperation("删除某个角色")
    @DeleteMapping("remove/{id}")
    public Result removeById(@PathVariable Long id){
        boolean isSuccess = roleService.removeById(id);
        if (isSuccess){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

    @ApiOperation("批量删除多个角色")
    @DeleteMapping("batchRemove")
    public Result removeRoles(@RequestBody List<Long> ids){
        boolean isSuccess = roleService.removeByIds(ids);
        if (isSuccess){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }
}
