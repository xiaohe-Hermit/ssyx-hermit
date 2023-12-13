package com.athermit.ssyx.product.service;

import com.athermit.ssyx.model.product.SkuImage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品图片 服务类
 * </p>
 *
 * @author athermit
 * @since 2023-08-14
 */
public interface SkuImageService extends IService<SkuImage> {

    List<SkuImage> selectImageListById(Long id);
}
