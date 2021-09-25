package com.atguigu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.yygh.hosp.Reposity.HospitalRepository;
import com.atguigu.yygh.hosp.service.HospitalService;
import com.atguigu.yygh.model.hosp.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public void save(Map<String, Object> stringObjectMap) {
        //判斷是否存在,並轉換對象
        String s = JSONObject.toJSONString(stringObjectMap);//變成字符串
        Hospital hospital = JSONObject.parseObject(s, Hospital.class);
        String hoscode = hospital.getHoscode();
        Hospital hospitalexist=hospitalRepository.getHospitalByHoscode(hoscode);
        //添加
        if (hospitalexist!=null){
            hospital.setStatus(hospitalexist.getStatus());
            hospital.setCreateTime(hospitalexist.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }else{
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
        //添加
    }
}
