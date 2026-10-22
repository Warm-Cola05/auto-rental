package com.coder.rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.AutoMaker;
import com.coder.rental.mapper.AutoMakerMapper;
import com.coder.rental.service.IAutoMakerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
@Service
public class AutoMakerServiceImpl extends ServiceImpl<AutoMakerMapper, AutoMaker> implements IAutoMakerService {
    @Override
    public Page<AutoMaker> search(int start, int size, AutoMaker autoMaker) {
        QueryWrapper<AutoMaker> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("order_letter")
                .like(StrUtil.isNotEmpty(autoMaker.getName()), "name",
                        autoMaker.getName());
        Page<AutoMaker> page= new Page<>(start, size);
        this.page(page, queryWrapper);
        return page;
    }
}
