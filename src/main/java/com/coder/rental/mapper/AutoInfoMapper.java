package com.coder.rental.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.AutoInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
public interface AutoInfoMapper extends BaseMapper<AutoInfo> {
    Page<AutoInfo> searchByPage(@Param("page") Page<AutoInfo> page,
                                @Param("autoInfo") AutoInfo autoInfo);

    List<AutoInfo> toBeMaintain();
}
