package com.coder.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.AutoInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
public interface IAutoInfoService extends IService<AutoInfo> {
    Page<AutoInfo> searchByPage(Page<AutoInfo> page, AutoInfo autoInfo);

    boolean delete(String ids);


    AutoInfo selectByAutoNum(String autoNum);

    List<AutoInfo> toBeMaintain();
}
