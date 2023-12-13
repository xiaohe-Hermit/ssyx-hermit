package com.athermit.ssyx.product.service;

import com.athermit.ssyx.model.product.SkuImage;
import com.athermit.ssyx.model.product.SkuPoster;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品海报表 服务类
 * </p>
 *
 * @author athermit
 * @since 2023-08-14
 */
public interface SkuPosterService extends IService<SkuPoster> {

    List<SkuPoster> selectPosterListById(Long id);

}
