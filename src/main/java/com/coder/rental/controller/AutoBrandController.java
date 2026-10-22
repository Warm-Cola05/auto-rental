package com.coder.rental.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.rental.entity.AutoBrand;
import com.coder.rental.service.IAutoBrandService;
import com.coder.rental.utils.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pat
 * @since 2026-06-23
 */
@RestController
@RequestMapping("/rental/autoBrand")
public class AutoBrandController {
    @Resource
    private IAutoBrandService autoBrandService;

    @PostMapping("/{start}/{size}")
    public Result search(@PathVariable int start,
                         @PathVariable int size,
                         @RequestBody AutoBrand autoBrand) {
        Page<AutoBrand> page = new Page<>(start, size);
        return Result.success()
                .setData(autoBrandService.searchByPage(page, autoBrand));
    }

    @PostMapping
    public Result save(@RequestBody AutoBrand autoBrand) {
        return autoBrandService.save(autoBrand) ?
                Result.success() : Result.fail();
    }

    @PutMapping
    public Result update(@RequestBody AutoBrand autoBrand) {
        return autoBrandService.updateById(autoBrand) ?
                Result.success() : Result.fail();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        List<Integer> list = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .toList();
        return autoBrandService.removeByIds(list) ?
                Result.success() : Result.fail();
    }
    @GetMapping("{makerId}")
    public Result selectByMakerId(@PathVariable Integer makerId) {
        return Result.success()
                .setData(autoBrandService.selectByMakerId(makerId));
    }
    @GetMapping("exportExcel")
    public void export(HttpServletResponse response) throws IOException {
        List<AutoBrand> list=autoBrandService.list();
        ExcelWriter writer= ExcelUtil.getWriter(true);
        writer.addHeaderAlias("brandName","品牌名称");
        writer.addHeaderAlias("deleted","是否删除");
        writer.write(list,true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset:utf-8");
        String fileName= URLEncoder.encode("汽车品牌", StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
        writer.flush(response.getOutputStream(),true);
        writer.close();
    }
}
