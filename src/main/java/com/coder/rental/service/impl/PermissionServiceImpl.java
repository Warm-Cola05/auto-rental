package com.coder.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coder.rental.entity.Permission;
import com.coder.rental.entity.User;
import com.coder.rental.mapper.PermissionMapper;
import com.coder.rental.mapper.UserMapper;
import com.coder.rental.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.rental.utils.RouteTreeUtils;
import com.coder.rental.vo.RolePermissionVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<Permission> selectPermissionListByUserId(Integer userId) {
        return baseMapper.selectPermissionListByUserId(userId);
    }

    @Override
    public List<Permission> selectList() {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("order_num");
        List<Permission> permissions = baseMapper.selectList(queryWrapper);
        return RouteTreeUtils.buildMenuTree(permissions, 0);
    }

    @Override
    public List<Permission> selectTree() {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("permission_type", 2);
        queryWrapper.orderByAsc("order_num");
        List<Permission> permissions = baseMapper.selectList(queryWrapper);
        Permission permission = new Permission();
        permission.setId(0).setPid(-1).setPermissionLabel("根目录");
        permissions.add(permission);
        return RouteTreeUtils.buildMenuTree(permissions, -1);
    }

    @Override
    public boolean hasChildren(Integer id) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", id);
        return baseMapper.selectCount(queryWrapper) > 0;
    }
    /**
     * 根据用户ID和角色ID，查询并构建权限树形结构。
     *
     * @param userId 用户ID，用于查询用户权限或判断是否为管理员。
     * @param roleId 角色ID，用于查询该角色已有的权限。
     * @return RolePermissionVo 角色权限视图对象，包含权限树和已选中的权限ID列表。
     */
    @Override
    public RolePermissionVo selectPermissionTree(Integer userId, Integer roleId) {
        User user = userMapper.selectById(userId);
        List<Permission> list = null;
        // 判断用户是否为管理员，管理员查询所有权限，非管理员查询个人权限
        if (user != null && user.getIsAdmin() == 1) {
            list = baseMapper.selectList(null);
        } else {
            list = baseMapper.selectPermissionListByUserId(userId);
        }
        // 将权限列表构建为树形结构
        List<Permission> permissionList = RouteTreeUtils.buildMenuTree(list, 0);
        // 查询角色已有的权限
        List<Permission> rolePermissionList = baseMapper.selectPermissionListByRoleId(roleId);
        // 找出用户权限中与角色权限的交集
        List<Permission> newList=new ArrayList<>(list);
        List<Integer> ids= new ArrayList<>(list.stream().map(Permission::getId).toList());
        List<Integer> ids1=rolePermissionList.stream().map(Permission::getId).toList();
        ids.retainAll(ids1);

        Object[] array = ids.toArray();
        // 构建角色权限视图对象并返回
        RolePermissionVo rolePermissionVo=new RolePermissionVo();
        rolePermissionVo.setCheckedList(array).setPermissionList(permissionList);
        return rolePermissionVo;

    }
}