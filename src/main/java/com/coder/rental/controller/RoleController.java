package com.coder.rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.Role;
import com.coder.rental.service.IPermissionService;
import com.coder.rental.service.IRoleService;
import com.coder.rental.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
@RestController
@RequestMapping("/rental/role")
public class RoleController {
    @Resource
    private IRoleService roleService;
    @Resource
    private IPermissionService permissionService;

    @PostMapping("{start}/{size}")
    public Result search(@PathVariable Integer start,
                         @PathVariable Integer size,
                         @RequestBody Role role) {
        //前端处理
        //从SecurityContextHolder中获取用户信息
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user=(User)authentication.getPrincipal();
        role.setCreaterId(user.getId());*/
        //从token中获取userId
       /* String token=request.getHeader("token");
        if (StrUtil.isEmpty(token)){
            token=request.getParameter("token");
        }
        Object claim = JwtUtils.parseToken(token).getClaim("userid");
        String userId=claim.toString();*/
        Page<Role> page = new Page<>(start, size);
        return Result.success(roleService.selectList(page, role));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('sys:role:add')")
    public Result save(@RequestBody Role role) {
        return roleService.save(role) ? Result.success() : Result.fail();
    }

    @PutMapping
    public Result update(@RequestBody Role role) {
        return roleService.updateById(role) ? Result.success() : Result.fail();
    }

    @GetMapping("hasUser/{id}")
    public Result hasUser(@PathVariable Integer id) {
        return roleService.hasUser(id) ? Result.success().setMessage("有") :
                Result.success().setMessage("无");
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        return roleService.delete(ids) ? Result.success() : Result.fail();
    }

    //获取菜单树结构 当前角色id,  用户id
    @GetMapping("permissionTree")
    public Result selectPermissionTree(Integer userId, Integer roleId) {
        return Result.success(permissionService.selectPermissionTree(userId, roleId));
    }

    //权限分配
    @GetMapping("{roleId}/{permissionIds}")
    public Result assignPermission(@PathVariable int roleId,
                                   @PathVariable String permissionIds) {
        List<Integer> list = Arrays.stream(permissionIds.split(","))
                .map(Integer::parseInt)
                .toList();
        return roleService.assignPermission(roleId, list) ?
                Result.success() : Result.fail();

    }

    /**
     * 获取所有角色，不分页
     * @return
     */
    @GetMapping
    public Result list() {
        return Result.success(roleService.list());
    }

}
