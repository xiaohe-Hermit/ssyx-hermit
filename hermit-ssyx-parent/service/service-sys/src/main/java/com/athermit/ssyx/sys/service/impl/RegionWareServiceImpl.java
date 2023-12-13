package com.athermit.ssyx.sys.service.impl;

import com.athermit.ssyx.common.exception.SsyxException;
import com.athermit.ssyx.common.result.ResultCodeEnum;
import com.athermit.ssyx.model.sys.RegionWare;
import com.athermit.ssyx.sys.mapper.RegionWareMapper;
import com.athermit.ssyx.sys.service.RegionWareService;
import com.athermit.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 城市仓库关联表 服务实现类
 * </p>
 *
 * @author athermit
 * @since 2023-08-11
 */
@Service
public class RegionWareServiceImpl extends ServiceImpl<RegionWareMapper, RegionWare> implements RegionWareService {

    @Override
    public IPage<RegionWare> selectPageRegionWare(Page<RegionWare> pageParam, RegionWareQueryVo regionWareQueryVo) {

        //获取查询条件
        String keyword = regionWareQueryVo.getKeyword();
        LambdaQueryWrapper<RegionWare> wrapper = new LambdaQueryWrapper<>();

        //判断条件是否为空，不为空则封装条件
        if (!StringUtils.isEmpty(keyword)) {
            //根据区域名称或仓库名称查询
            wrapper.like(RegionWare::getRegionName, keyword).or().like(RegionWare::getWareName, keyword);
        }

        //调用方法实现分页查询
        Page<RegionWare> regionWarePage = baseMapper.selectPage(pageParam, wrapper);

        //返回数据
        return regionWarePage;
    }

    @Override
    public void saveRegionWare(RegionWare role) {
        //判断区域是否已经开通了
        LambdaQueryWrapper<RegionWare> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RegionWare::getRegionId, role.getRegionId());
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            //已存在
            throw new SsyxException(ResultCodeEnum.REGION_OPEN);
        }
        baseMapper.insert(role);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        RegionWare regionWare = baseMapper.selectById(id);
        regionWare.setStatus(status);
        baseMapper.updateById(regionWare);
    }
}
