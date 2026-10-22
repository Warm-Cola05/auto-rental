package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.AutoMaker;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
public interface IAutoMakerService extends IService<AutoMaker> {

    Page<AutoMaker> search(int start, int size, com.coder.rental.entity.AutoMaker autoMaker);
}
