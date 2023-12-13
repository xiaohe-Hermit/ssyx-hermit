package com.athermit.ssyx.product.service;

import com.athermit.ssyx.model.product.Category;
import com.athermit.ssyx.vo.product.CategoryQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author athermit
 * @since 2023-08-14
 */
public interface CategoryService extends IService<Category> {

    IPage<Category> selectCategoryPage(Page<Category> pageParam, CategoryQueryVo categoryQueryVo);
}
