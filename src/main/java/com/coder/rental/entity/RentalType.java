package com.coder.rental.entity;

        import com.baomidou.mybatisplus.annotation.*;

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
    @TableName("busi_rental_type")
@Schema(name = "RentalType", description = "")
    public class RentalType implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "出租类型id")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        @Schema(description = "出租类型名称")
    private String typeName;

        @Schema(description = "享受折扣")
    private Double typeDiscount;

    @TableField(exist = false)
    private Double lowDiscount;

    @TableField(exist = false)
    private Double highDiscount;

        @Schema(description = "备注")
    private String remark;

        @Schema(description = "创建时间")
        @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

        @Schema(description = "修改时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

        @Schema(description = "是否删除")
    private Boolean deleted;

    @TableField(exist = false)
    private String makerName;
    @TableField(exist = false)
    private String brandName;
    @TableField(exist = false)
    private Double lowRent;
    @TableField(exist = false)
    private Double highRent;
    @TableField(exist = false)
    private LocalDate lowRegistrationDate;
    @TableField(exist = false)
    private LocalDate highRegistrationDate;

}