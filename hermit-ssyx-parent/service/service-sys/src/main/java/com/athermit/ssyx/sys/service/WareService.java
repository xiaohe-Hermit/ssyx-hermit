package com.athermit.ssyx.sys.service;

import com.athermit.ssyx.model.sys.Ware;
import com.athermit.ssyx.vo.product.WareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 仓库表 服务类
 * </p>
 *
 * @author athermit
 * @since 2023-08-11
 */
public interface WareService extends IService<Ware> {

    IPage<Ware> selectPageWare(Page<Ware> pageParam, WareQueryVo wareQueryVo);
}
