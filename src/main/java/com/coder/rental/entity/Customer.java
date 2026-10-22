package com.coder.rental.entity;

        import com.baomidou.mybatisplus.annotation.IdType;
        import com.baomidou.mybatisplus.annotation.TableField;
        import com.baomidou.mybatisplus.annotation.TableId;
        import com.baomidou.mybatisplus.annotation.TableName;
        import java.io.Serializable;
        import java.time.LocalDate;
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
    @TableName("busi_customer")
@Schema(name = "Customer", description = "")
    public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "客户id")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        @Schema(description = "客户姓名")
    private String name;

        @Schema(description = "客户年龄")
    private Integer age;

    @Schema(description = "性别 0 女 1 男")
    private Integer gender;

        @Schema(description = "手机号码")
    private String tel;

        @Schema(description = "出生日期")
    private LocalDate birthday;

        @Schema(description = "身份证号码")
    private String idNum;

        @Schema(description = "客户状态 0 黑名单 1白名单")
    private Boolean status;

        @Schema(description = "创建时间")
    private LocalDateTime createTime;

        @Schema(description = "修改时间")
    private LocalDateTime updateTime;

        @Schema(description = "是否删除")
    private Boolean deleted;

        @TableField(exist = false)
    private Integer lowAge;

        @TableField(exist = false)
    private Integer highAge;

}