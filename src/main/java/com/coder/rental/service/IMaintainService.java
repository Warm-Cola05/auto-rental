package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.Maintain;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
public interface IMaintainService extends IService<Maintain> {
    Page<Maintain> searchByPage(Page<Maintain> page, Maintain maintain);

    boolean delete(String ids);
}
