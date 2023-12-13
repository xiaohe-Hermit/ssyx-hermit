package com.athermit.ssyx.sys.service.impl;

import com.athermit.ssyx.model.sys.Ware;
import com.athermit.ssyx.sys.mapper.WareMapper;
import com.athermit.ssyx.sys.service.WareService;
import com.athermit.ssyx.vo.product.WareQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 仓库表 服务实现类
 * </p>
 *
 * @author athermit
 * @since 2023-08-11
 */
@Service
public class WareServiceImpl extends ServiceImpl<WareMapper, Ware> implements WareService {

    @Override
    public IPage<Ware> selectPageWare(Page<Ware> pageParam, WareQueryVo wareQueryVo) {
        //查询条件
        String keyword = wareQueryVo.getName();
        LambdaQueryWrapper<Ware> wrapper = new LambdaQueryWrapper<>();

        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like(Ware::getName, keyword);
        }

        Page<Ware> warePage = baseMapper.selectPage(pageParam, wrapper);

        return warePage;

    }
}
