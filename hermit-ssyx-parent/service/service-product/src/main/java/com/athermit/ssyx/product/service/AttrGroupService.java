package com.athermit.ssyx.product.service;

import com.athermit.ssyx.model.product.AttrGroup;
import com.athermit.ssyx.vo.product.AttrGroupQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 属性分组 服务类
 * </p>
 *
 * @author athermit
 * @since 2023-08-14
 */
public interface AttrGroupService extends IService<AttrGroup> {

    IPage<AttrGroup> selectAttrGroupPage(Page<AttrGroup> pageParam, AttrGroupQueryVo attrGroupQueryVo);

    List<AttrGroup> selectAllAttrGroupList();
}
