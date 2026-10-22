package com.coder.rental.entity;

        import com.baomidou.mybatisplus.annotation.*;

        import java.io.Serializable;
        import java.time.LocalDate;
        import java.time.LocalDateTime;

        import com.fasterxml.jackson.annotation.JsonFormat;
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
    @TableName("busi_maintain")
@Schema(name = "Maintain", description = "")
    public class Maintain implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "保养id")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        @Schema(description = "车辆id")
    private Integer autoId;

    private String autoNum;

        @Schema(description = "维保时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime maintainTime;

        @Schema(description = "维保地点")
    private String location;

        @Schema(description = "维保项目")
    private String item;

        @Schema(description = "维保费用")
    private Integer cost;

        @Schema(description = "创建时间")
        @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

        @Schema(description = "更新时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

        @Schema(description = "是否删除")
    private Boolean deleted;

    @TableField(exist = false)
    private Integer lowCost;
    @TableField(exist = false)
    private Integer highCost;

    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lowMaintainTime;
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime highMaintainTime;
}