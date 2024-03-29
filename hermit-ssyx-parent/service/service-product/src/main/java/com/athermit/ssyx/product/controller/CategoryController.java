package com.athermit.ssyx.product.controller;


import com.athermit.ssyx.common.result.Result;
import com.athermit.ssyx.model.product.Category;
import com.athermit.ssyx.product.service.CategoryService;
import com.athermit.ssyx.vo.product.CategoryQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品三级分类 前端控制器
 * </p>
 *
 * @author athermit
 * @since 2023-08-14
 */
@Api(tags = "商品类型接口")
@RestController
@RequestMapping("/admin/product/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("商品分类列表")
    @GetMapping("{page}/{limit}")
    public Result getPageList(@PathVariable Long page, @PathVariable Long limit, CategoryQueryVo categoryQueryVo){

        Page<Category> pageParam = new Page<>(page,limit);
        IPage<Category> pageModel1 = categoryService.selectCategoryPage(pageParam,categoryQueryVo);
        return Result.ok(pageModel1);
    }

    @ApiOperation(value = "获取商品分类信息")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return Result.ok(category);
    }

    @ApiOperation(value = "新增商品分类")
    @PostMapping("save")
    public Result save(@RequestBody Category category) {
        categoryService.save(category);
        return Result.ok(null);
    }

    @ApiOperation(value = "修改商品分类")
    @PutMapping("update")
    public Result updateById(@RequestBody Category category) {
        categoryService.updateById(category);
        return Result.ok(null);
    }

    @ApiOperation(value = "删除商品分类")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        categoryService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation(value = "根据id列表删除商品分类")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        categoryService.removeByIds(idList);
        return Result.ok(null);
    }

    @ApiOperation("获取全部商品分类")
    @GetMapping("findAllList")
    public Result findAllList(){
        List<Category> categoryList = categoryService.list();
        return Result.ok(categoryList);
    }

}

