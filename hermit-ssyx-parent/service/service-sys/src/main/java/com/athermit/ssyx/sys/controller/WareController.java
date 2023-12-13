package com.athermit.ssyx.sys.controller;


import com.athermit.ssyx.common.result.Result;
import com.athermit.ssyx.model.sys.Ware;
import com.athermit.ssyx.sys.mapper.WareMapper;
import com.athermit.ssyx.sys.service.WareService;
import com.athermit.ssyx.vo.product.WareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author athermit
 * @since 2023-08-11
 */
@Api(tags = "仓库接口")
@RestController
@RequestMapping("/admin/sys/ware")
@CrossOrigin
public class WareController {

    @Autowired
    private WareService wareService;

    @ApiOperation("获取仓库列表分页")
    @GetMapping("{page}/{limit}")
    public Result getPageList(@PathVariable Long page, @PathVariable Long limit, WareQueryVo wareQueryVo) {
        Page<Ware> pageParam = new Page<>(page, limit);
        IPage<Ware> pageModel1 = wareService.selectPageWare(pageParam, wareQueryVo);
        return Result.ok(pageModel1);
    }

    @ApiOperation("通过id获取仓库")
    @GetMapping("get/{id}")
    public Result getById(@PathVariable Long id) {
        Ware ware = wareService.getById(id);
        if (ware != null) {
            return Result.ok(ware);
        } else {
            return Result.fail(null);
        }
    }

    @ApiOperation("添加仓库")
    @PostMapping("save")
    public Result save(@RequestBody Ware role) {
        boolean success = wareService.save(role);
        if (success) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    @ApiOperation("更新仓库")
    @PutMapping("update")
    public Result updateById(@RequestBody Ware role) {
        boolean success = wareService.updateById(role);
        if (success) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }


    @ApiOperation("通过id删除仓库")
    @DeleteMapping("remove/{id}")
    public Result removeById(@PathVariable Long id) {
        boolean success = wareService.removeById(id);
        if (success) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }


    @ApiOperation("批量删除仓库")
    @DeleteMapping("batchRemove")
    public Result removeRows(@RequestBody List<Long> ids) {
        boolean success = wareService.removeByIds(ids);
        if (success) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    @ApiOperation("获取所有仓库列表")
    @GetMapping("findAllList")
    public Result findAllList() {
        List<Ware> list = wareService.list();
        return Result.ok(list);
    }


}

