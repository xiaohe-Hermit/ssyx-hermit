package com.athermit.ssyx.product.service.impl;

import com.athermit.ssyx.model.product.Comment;
import com.athermit.ssyx.product.mapper.CommentMapper;
import com.athermit.ssyx.product.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价 服务实现类
 * </p>
 *
 * @author athermit
 * @since 2023-08-14
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
