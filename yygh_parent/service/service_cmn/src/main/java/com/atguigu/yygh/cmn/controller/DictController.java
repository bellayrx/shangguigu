package com.atguigu.yygh.cmn.controller;

import com.atguigu.yygh.cmn.service.impl.DictServiceImpl;
import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.cmn.service.DictService;
import com.atguigu.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Api(value = "数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
public class DictController {
    @Autowired
    private DictServiceImpl dictService;
    //根据数据id查询子数据列表
    @ApiOperation(value = "查询列表")
    @GetMapping("/findChildData/{id}")
    public Result findChildData(@PathVariable Long id){
        List<Dict> list=dictService.findChildData(id);
        return Result.ok(list);
    }
    //导出
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response){
        dictService.exportData(response);
    }
    //导入
    @PostMapping("/importData")
    public Result importData(MultipartFile file){
        dictService.importData(file);
        return Result.ok();
    }
}
