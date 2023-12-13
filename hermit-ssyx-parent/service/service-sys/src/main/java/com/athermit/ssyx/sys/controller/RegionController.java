package com.athermit.ssyx.sys.controller;


import com.athermit.ssyx.common.result.Result;
import com.athermit.ssyx.model.sys.Region;
import com.athermit.ssyx.sys.service.RegionService;
import com.athermit.ssyx.sys.service.RegionWareService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 * @author athermit
 * @since 2023-08-11
 */
@Api(tags = "地区接口")
@RestController
@RequestMapping("/admin/sys/region")
@CrossOrigin
public class RegionController {

    @Autowired
    private RegionService regionService;

    @ApiOperation("根据关键词查找地区")
    @GetMapping("findRegionByKeyword/{keyword}")
    public Result findRegionByKeyword(@PathVariable String keyword) {
        LambdaQueryWrapper<Region> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)){
            wrapper.like(Region::getName,keyword);
        }
        List<Region> region = regionService.list(wrapper);
        return Result.ok(region);
    }

    @ApiOperation("根据pid查找地区")
    @GetMapping("findByParentId/{parentId}")
    public Result findByParentId(@PathVariable Long parentId){
        LambdaQueryWrapper<Region> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(parentId)){
            wrapper.eq(Region::getParentId,parentId);
        }
        List<Region> region = regionService.list(wrapper);

        return Result.ok(region);
    }

}

