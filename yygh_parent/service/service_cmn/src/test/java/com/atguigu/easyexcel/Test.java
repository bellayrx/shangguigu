package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        //写道excel，文件路径以及文件名称
        String fileName= "E:\\mycode\\shang\\01.xlsx";
        List<UserData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserData userData = new UserData();
            userData.setUid(i);
            userData.setUsername("lucy"+i);
            list.add(userData);
        }
        //调用方法
        EasyExcel.write(fileName,UserData.class).sheet("用户信息")
                .doWrite(list);
    }
}
