package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.common.utils.MD5;
import com.atguigu.yygh.hosp.service.impl.HospitalSetServiceImpl;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.vo.hosp.HospitalSetQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;
import java.util.Random;

@Api(tags = "医院管理")
@CrossOrigin
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
    @DeleteMapping("/{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean b = service.removeById(id);
        if(b){
            return Result.ok(b);
        }else {
            return Result.fail();
        }
    }

    /*
    * 條件查詢帶分頁 條件封裝 vo包裏面的
    * */
    @ApiOperation(value = "條件查詢帶分頁")
    @PostMapping("/findPage/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){
        //創建page對象,這個是mp的 加上requestbody不是必須值
        //接收前端参数使用的@RequestParam，这是springmvc提供的，@Param是mybatis里传递参数提供的，他们使用的地方不一样”
        Page<HospitalSet> page =new Page<>(current,limit);
        //構造條件
        QueryWrapper wrapper =  new QueryWrapper();
        String hosname = hospitalSetQueryVo.getHosname();
        String hoscode = hospitalSetQueryVo.getHoscode();
        if(!StringUtils.isEmpty(hosname)){//不加會空指針
            wrapper.like("hosname",hospitalSetQueryVo.getHosname());
        }
        if(!StringUtils.isEmpty(hoscode)){//不加會空指針
            wrapper.eq("hoscode",hospitalSetQueryVo.getHoscode());
        }
        //調用方法
        //
        Page<HospitalSet> page1 = service.page(page, wrapper);
        return Result.ok(page1);
    }
    /*
    *
    * 添加醫院設置
    * */
    @ApiOperation(value = "添加醫院設置")
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet){
        //設置狀態 1可以 0 不可
        hospitalSet.setStatus(1);
        //簽名密鑰
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(100)));
        boolean save = service.save(hospitalSet);
        if(save){
            return Result.ok(save);
        }
        else{
            return Result.fail();
        }
    }


    /*
    * 修改醫院設置
    *根據ID獲取
    * */
    @ApiOperation(value = "根據ID獲取")
    @GetMapping("getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id){
        HospitalSet byId = service.getById(id);
        return Result.ok(byId);

    }
    @ApiOperation(value = "修改醫院設置")
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){
        boolean b = service.updateById(hospitalSet);
        if(b){
            return Result.ok();
        }
        else {
            return Result.fail();
        }
    }
    /*
    * 批量刪除
    * */
    @ApiOperation(value = "批量刪除")
    @DeleteMapping("batchRemoveHospitalSet")
    public Result batchRemoveHospitalSet(@RequestBody  List<Long> idList){
        boolean b = service.removeByIds(idList);
        if(b){
            return Result.ok();
        }
        else {
            return Result.fail();
        }
    }
    //醫院設置所動和解鎖
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,@PathVariable Integer status){
        //根據ID查詢信息
        HospitalSet byId = service.getById(id);
        byId.setStatus(status);
        service.updateById(byId);
        return Result.ok();
    }
    //發送簽名密鑰
    @PutMapping("sendHospitalSet/{id}/")
    public Result sendHospitalSet(@PathVariable Long id){
        HospitalSet hospitalSet = service.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //TODO: 發送短信
        return Result.ok();
    }
}
