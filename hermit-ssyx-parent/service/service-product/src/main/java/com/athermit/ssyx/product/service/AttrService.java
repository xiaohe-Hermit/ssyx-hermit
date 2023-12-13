package com.athermit.ssyx.product.service;

import com.athermit.ssyx.model.product.Attr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author athermit
 * @since 2023-08-14
 */
public interface AttrService extends IService<Attr> {

    List<Attr> selectAttrListByGroupId(Long groupId);
}
