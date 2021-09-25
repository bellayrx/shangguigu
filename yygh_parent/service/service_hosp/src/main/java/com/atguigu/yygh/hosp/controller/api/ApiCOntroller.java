package com.atguigu.yygh.hosp.controller.api;

import com.atguigu.yygh.common.helper.HttpRequestHelper;
import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.hosp.service.HospitalService;
import com.atguigu.yygh.model.hosp.Hospital;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(tags = "医院管理API接口")
@RestController
@RequestMapping("/api/hosp")

public class ApiCOntroller {
    @Autowired
    private HospitalService hospitalService;
    //上傳醫院接口
    @ApiOperation(value = "修改")
    @PostMapping("/saveHosp")
    public Result saveHosp(@RequestBody Map<String, String[]> hospital){
        //獲取傳遞過來的信息
        //Map<String, String[]> requestMap = hospital;
        Map<String, Object> stringObjectMap = HttpRequestHelper.switchMap(hospital);
        //調用service方法
        hospitalService.save(stringObjectMap);
        return Result.ok();
    }

}
