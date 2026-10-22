package com.coder.rental.entity;

        import com.baomidou.mybatisplus.annotation.*;

        import java.io.Serializable;
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
    @TableName("busi_violation")
@Schema(name = "Violation", description = "")
    public class Violation implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "违章id")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        @Schema(description = "车辆id")
    private Integer autoId;
        @Schema(description = "车牌号")
    private String autoNum;

    @Schema(description = "违章时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime vtime;

        @Schema(description = "违章事由")
    private String reason;

        @Schema(description = "违章地点")
    private String location;

        @Schema(description = "扣分")
    private Integer deductPoints;

        @Schema(description = "罚款")
    private Integer fine;

        @Schema(description = "是否处理 0 -未处理 1-已处理")
    private Boolean status;

        @Schema(description = "创建时间")
        @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

        @Schema(description = "修改时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

        @Schema(description = "是否删除")
    private Boolean deleted;



    @TableField(exist = false)
    private Integer lowFine;
    @TableField(exist = false)
    private Integer highFine;
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lowVtime;
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime highVtime;
}