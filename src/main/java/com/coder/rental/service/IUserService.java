package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
public interface IUserService extends IService<User> {
    User selectByUsername(String username);
    List<String> selectRoleName(int id);

    Page<User> searchByPage(Page<User> page, User user);

    boolean delete(String ids);

    boolean bindRole(Integer userId, List<Integer> list);
}
