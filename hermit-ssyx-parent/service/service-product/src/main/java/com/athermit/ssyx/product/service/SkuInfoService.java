package com.athermit.ssyx.product.service;


import com.athermit.ssyx.model.product.SkuInfo;
import com.athermit.ssyx.vo.product.SkuInfoQueryVo;
import com.athermit.ssyx.vo.product.SkuInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * sku信息 服务类
 * </p>
 *
 * @author athermit
 * @since 2023-08-14
 */
public interface SkuInfoService extends IService<SkuInfo> {

    IPage<SkuInfo> selectSkuInfoPage(Page<SkuInfo> pageParam, SkuInfoQueryVo skuInfoQueryVo);

    void saveSkuInfoVo(SkuInfoVo skuInfoVo);

    SkuInfoVo selectSkuInfoById(Long id);

    void updateSkuInfo(SkuInfoVo skuInfoVo);

    void check(Long skuId, Integer status);

    void publish(Long skuId, Integer status);

    void isNewPerson(Long skuId, Integer status);
}
