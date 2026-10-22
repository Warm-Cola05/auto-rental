package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
public interface ICustomerService extends IService<Customer> {
    boolean delete(String ids);

    Page<Customer> searchByPage(Page<Customer> page, Customer customer);

    Customer selectByCustomerByTel(Customer customer);
}
