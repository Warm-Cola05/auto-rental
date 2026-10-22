package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.Role;
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
public interface IRoleService extends IService<Role> {
    Page<Role> selectList(Page<Role> page, Role role);
    boolean hasUser(Integer id);
    boolean delete(String ids);

    public boolean assignPermission(Integer roleId, List<Integer> permissionIds);

    List<Integer> selectRoleIdByUserId(Integer userId);
}
