package com.coder.rental.entity;

        import com.baomidou.mybatisplus.annotation.*;

        import java.io.Serializable;
        import java.time.LocalDateTime;
        import java.util.ArrayList;
        import java.util.List;

        import com.fasterxml.jackson.annotation.JsonInclude;
        import io.swagger.v3.oas.annotations.media.Schema;

        import lombok.Data;
        import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author pat
* @since 2026-06-23
*/
    @Data
    @Accessors(chain = true)
    @TableName("sys_permission")
    @Schema(name = "Permission", description = "")
    public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "权限id")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        @Schema(description = "权限名称")
    private String permissionLabel;

        @Schema(description = "父权限id")
    private Integer pid;

        @Schema(description = "父权限名称")
    private String parentLabel;

        @Schema(description = "权限标识")
    private String permissionCode;

        @Schema(description = "权限路由地址")
    private String routePath;

        @Schema(description = "权限路由名称")
    private String routeName;

        @Schema(description = "权限路径")
    private String routeUrl;

        @Schema(description = "权限类型 权限类型 0-	根目录 1-子目录 2-按钮级别")
    private Integer permissionType;

        @Schema(description = "图标地址")
    private String icon;

        @Schema(description = "排序")
    private Integer orderNum;

        @Schema(description = "创建时间")
        @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

        @Schema(description = "更新时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

        @Schema(description = "备注")
    private String remark;

        @Schema(description = "是否删除")
    private Boolean deleted;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)//序列化时，为空的属性不序列化
    // 子权限
    private List<Permission> children;
}