package com.athermit.ssyx.sys.controller;


import com.athermit.ssyx.common.result.Result;
import com.athermit.ssyx.model.sys.RegionWare;
import com.athermit.ssyx.sys.service.RegionWareService;
import com.athermit.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 城市仓库关联表 前端控制器
 * </p>
 *
 * @author athermit
 * @since 2023-08-11
 */
@Api(tags = "开通区域接口")
@RestController
@RequestMapping("/admin/sys/regionWare")
@CrossOrigin
public class RegionWareController {

    @Autowired
    private RegionWareService regionWareService;

    @ApiOperation("获取区域列表")
    @GetMapping("{page}/{limit}")
    public Result getPageList(@PathVariable Long page, @PathVariable Long limit, RegionWareQueryVo regionWareQueryVo) {

        Page<RegionWare> pageParam = new Page<>(page, limit);
        IPage<RegionWare> pageModel1 = regionWareService.selectPageRegionWare(pageParam, regionWareQueryVo);

        return Result.ok(pageModel1);
    }

    @ApiOperation("添加开通区域")
    @PostMapping("save")
    public Result save(@RequestBody RegionWare role) {

        regionWareService.saveRegionWare(role);

        return Result.ok(null);
    }

    @ApiOperation("删除开通区域")
    @DeleteMapping("remove/{id}")
    public Result removeById(@PathVariable Long id) {
        boolean success = regionWareService.removeById(id);
        if (success) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    @ApiOperation("取消开通区域")
    @PostMapping("updateStatus/{id}/{status}")
    public Result save(@PathVariable Long id, @PathVariable Integer status) {
        regionWareService.updateStatus(id, status);
        return Result.ok(null);
    }

    @ApiOperation("通过id获取开通区域")
    @GetMapping("get/{id}")
    public Result getById(@PathVariable Long id) {
        RegionWare regionWare = regionWareService.getById(id);
        if (regionWare != null) {
            return Result.ok(regionWare);
        } else {
            return Result.fail(null);
        }
    }

    @ApiOperation("通过id获取开通区域")
    @DeleteMapping("batchRemove")
    public Result removeRows(@RequestBody List<Long> idList) {
        boolean success = regionWareService.removeByIds(idList);
        if (success) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }


}

