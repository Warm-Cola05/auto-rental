package com.coder.rental.entity;

        import com.baomidou.mybatisplus.annotation.IdType;
        import com.baomidou.mybatisplus.annotation.TableField;
        import com.baomidou.mybatisplus.annotation.TableId;
        import com.baomidou.mybatisplus.annotation.TableName;
        import java.io.Serializable;
        import java.util.List;

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
    @TableName("sys_dept")
@Schema(name = "Dept", description = "")
    public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "部门id")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        @Schema(description = "部门名称")
    private String deptName;

        @Schema(description = "联系电话")
    private String phone;

        @Schema(description = "部门地址")
    private String address;

        @Schema(description = "上级部门id")
    private Integer pid;

        @Schema(description = "上级部门名称")
    private String parentName;

        @Schema(description = "排序号")
    private Integer orderNum;

        @Schema(description = "是否删除")
    private Boolean deleted;
        @TableField(exist = false)
    private List<Dept> children;
}