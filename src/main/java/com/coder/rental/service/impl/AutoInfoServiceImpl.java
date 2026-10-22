package com.coder.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.AutoInfo;
import com.coder.rental.mapper.AutoInfoMapper;
import com.coder.rental.service.IAutoInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.rental.service.IOssService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
@Service
public class AutoInfoServiceImpl extends ServiceImpl<AutoInfoMapper, AutoInfo> implements IAutoInfoService {
    @Resource
    private IOssService ossService;
    @Override
    public Page<AutoInfo> searchByPage(Page<AutoInfo> page, AutoInfo autoInfo) {
        return baseMapper.searchByPage(page, autoInfo);
    }

    @Override
    public boolean delete(String ids) {
        List<Integer> list = Arrays.stream(ids.split(",")).map(Integer::parseInt).toList();
        if (!list.isEmpty()){
            //同时去删除图片
            list.forEach(id -> {
                AutoInfo autoInfo = baseMapper.selectById(id);
                if (autoInfo != null){
                    String pic = autoInfo.getPic();
                    if (pic != null){
                        //删除oss中的图片
                        ossService.delete(pic);
                    }
                }
            });
            return baseMapper.deleteBatchIds(list) > 0;
        }
        return false;
    }

    @Override
    public AutoInfo selectByAutoNum(String autoNum) {
        QueryWrapper<AutoInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("auto_num", autoNum);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<AutoInfo> toBeMaintain() {
        return baseMapper.toBeMaintain();
    }
}
