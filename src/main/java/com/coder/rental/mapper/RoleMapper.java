package com.coder.rental.mapper;

import com.coder.rental.entity.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    List<Integer> selectRoleIdByUserId(Integer userId);
}
