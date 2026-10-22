package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.RentalType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
public interface IRentalTypeService extends IService<RentalType> {
    Page<RentalType> searchByPage(Page<RentalType> page, RentalType rentalType);

    boolean delete(String ids);

}
