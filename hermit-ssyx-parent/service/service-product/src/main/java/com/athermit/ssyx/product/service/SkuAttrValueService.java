package com.athermit.ssyx.product.service;

import com.athermit.ssyx.model.product.SkuAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * spu属性值 服务类
 * </p>
 *
 * @author athermit
 * @since 2023-08-14
 */
public interface SkuAttrValueService extends IService<SkuAttrValue> {

    List<SkuAttrValue> selectAttrValueListById(Long id);
}
