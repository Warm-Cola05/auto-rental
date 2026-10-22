package com.coder.rental.entity;

        import com.baomidou.mybatisplus.annotation.IdType;
        import com.baomidou.mybatisplus.annotation.TableField;
        import com.baomidou.mybatisplus.annotation.TableId;
        import com.baomidou.mybatisplus.annotation.TableName;
        import java.io.Serializable;
        import java.time.LocalDateTime;

        import com.fasterxml.jackson.annotation.JsonFormat;
        import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
        import com.fasterxml.jackson.databind.annotation.JsonSerialize;
        import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
        import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
    @TableName("busi_order")
@Schema(name = "Order", description = "")
    public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "订单id")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        @Schema(description = "订单编号")
    private String orderNum;

        @Schema(description = "关联车辆id")
    private Integer autoId;

        @Schema(description = "客户id")
    private Integer customerId;

        @Schema(description = "出租时间")
    private LocalDateTime rentalTime;

        @Schema(description = "出租类型")
    private Integer typeId;

        @Schema(description = "日租金额")
    private Integer rent;

        @Schema(description = "押金")
    private Integer deposit;

        @Schema(description = "起租里程")
    private Integer mileage;

        @Schema(description = "归还时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime returnTime;

        @Schema(description = "归还里程")
    private Integer returnMileage;

        @Schema(description = "应付租金")
    private Integer rentPayable;

        @Schema(description = "实付租金")
    private Integer rentActual;

        @Schema(description = "押金返还状态 0 未返还	1已返还")
    private Integer depositReturn;

        @Schema(description = "创建时间")
    private LocalDateTime createTime;

        @Schema(description = "更新时间")
    private LocalDateTime updateTime;

        @Schema(description = "是否删除")
    private Boolean deleted;

    @TableField(exist = false)
    private String customerName;
    @TableField(exist = false)
    private String autoNum;
    @TableField(exist = false)
    private String typeName;
    @TableField(exist = false)
    private String customerTel;
    @TableField(exist = false)
    private Double typeDiscount;
}