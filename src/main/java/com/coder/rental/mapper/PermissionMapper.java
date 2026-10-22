package com.coder.rental.mapper;

import com.coder.rental.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> selectPermissionListByUserId(Integer userId);

    List<Permission> selectPermissionListByRoleId(Integer roleId);
}
