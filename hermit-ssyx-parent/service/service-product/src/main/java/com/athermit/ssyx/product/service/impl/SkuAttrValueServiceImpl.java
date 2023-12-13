package com.athermit.ssyx.product.service.impl;

import com.athermit.ssyx.model.product.SkuAttrValue;
import com.athermit.ssyx.product.mapper.SkuAttrValueMapper;
import com.athermit.ssyx.product.service.SkuAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * spu属性值 服务实现类
 * </p>
 *
 * @author athermit
 * @since 2023-08-14
 */
@Service
public class SkuAttrValueServiceImpl extends ServiceImpl<SkuAttrValueMapper, SkuAttrValue> implements SkuAttrValueService {

    @Override
    public List<SkuAttrValue> selectAttrValueListById(Long id) {
        LambdaQueryWrapper<SkuAttrValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SkuAttrValue::getSkuId,id);
        List<SkuAttrValue> skuAttrValues = baseMapper.selectList(wrapper);
        return skuAttrValues;
    }
}
