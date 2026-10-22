package com.coder.rental.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
public interface OrderMapper extends BaseMapper<Order> {
    Page<Order> searchUnfinished(@Param("page") Page<Order> page, @Param("order") Order order);

    Page<Order> search(@Param("page")Page<Order> page, @Param("order") Order order);

    long countViolation(Integer autoId);
}
