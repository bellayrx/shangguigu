package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.hosp.service.impl.HospitalSetServiceImpl;
import com.atguigu.yygh.model.hosp.HospitalSet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "医院管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    @Autowired
    private HospitalSetServiceImpl service;

    @ApiOperation(value = "获取列表")
    @GetMapping("/findall")
    public  Result getAllHospSet(){
        //調用service
        List<HospitalSet> list = service.list();

        return Result.ok(list);
    }
    //删除
    @ApiOperation(value = "逻辑删除")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean b = service.removeById(id);
        if(b){
            return Result.ok(b);
        }else {
            return Result.fail();
        }
    }

    /*
    * 條件查詢帶分頁
    * */


    /*
    *
    * 添加醫院設置
    * */



    /*
    * 修改醫院設置
    *根據ID獲取
    * */


    /*
    * 批量刪除
    * */
}
