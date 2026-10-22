package com.coder.rental.entity;

        import com.baomidou.mybatisplus.annotation.IdType;
        import com.baomidou.mybatisplus.annotation.TableId;
        import com.baomidou.mybatisplus.annotation.TableName;
        import java.io.Serializable;
        import java.time.LocalDateTime;
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
    @TableName("sys_role")
@Schema(name = "Role", description = "")
    public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "角色id")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        @Schema(description = "角色编码")
    private String roleCode;

        @Schema(description = "角色名称")
    private String roleName;

        @Schema(description = "创建人id")
    private Integer createrId;

        @Schema(description = "创建时间")
    private LocalDateTime createTime;

        @Schema(description = "修改时间")
    private LocalDateTime updateTime;

        @Schema(description = "备注")
    private String remark;

        @Schema(description = "是否删除")
    private Boolean deleted;
}