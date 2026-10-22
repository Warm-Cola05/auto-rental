package com.coder.rental.entity;

        import com.baomidou.mybatisplus.annotation.TableId;
        import com.baomidou.mybatisplus.annotation.TableName;
        import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;

    import lombok.Getter;
    import lombok.Setter;
        import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author pat
* @since 2026-06-23
*/
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_role_permission")
@Schema(name = "RolePermission", description = "")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色id")
    private Integer roleId;

    @Schema(description = "权限资源id")
    private Integer permissionId;
}