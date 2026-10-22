package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
public interface IOrderService extends IService<Order> {
    boolean insert(Order order);

    Page<Order> searchUnfinished(Page<Order> page, Order order);

    boolean returnAuto(Order order,Integer maintainMileage);

    Page<Order> search(Page<Order> page, Order order);

    long countViolation(Integer autoId);
}
