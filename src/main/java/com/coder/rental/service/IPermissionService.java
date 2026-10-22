package com.coder.rental.service;

import com.coder.rental.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coder.rental.vo.RolePermissionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
public interface IPermissionService extends IService<Permission> {
    List<Permission> selectPermissionListByUserId(Integer userId);
    List<Permission> selectList();
    List<Permission> selectTree();
    boolean hasChildren(Integer id);

    RolePermissionVo selectPermissionTree(Integer userId, Integer roleId);
}
