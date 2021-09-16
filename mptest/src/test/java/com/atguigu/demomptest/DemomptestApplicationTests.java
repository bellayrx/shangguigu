package com.atguigu.demomptest;

import com.atguigu.demomptest.entity.User;
import com.atguigu.demomptest.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemomptestApplicationTests {
@Autowired
private UserMapper userMapper;
/*
* 爲什麽報錯，因爲是動態生成
* */
    @Test
    public void findall() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
        @Test
    public void insertUser(){
        User user =  new User();
        user.setName("wkm");
        user.setAge(15);
        user.setEmail("7@qq.com");
        user.setId(3);
        int insert = userMapper.insert(user);
        System.out.println(insert);
            /*
            * 這是一個影響函數
            * */
        }

        @Test
    public void update() {
            User user = new User();
            user.setId(6);
            user.setName("4");
            int i = userMapper.updateById(user);
            System.out.println(i);
        }

    @Test
    public void locker() {
        //根據ID查詢
        User user = userMapper.selectById(7);
        //修改
        user.setName("張三");
        userMapper.updateById(user);
    }
    /*
    * 刪除--根據ID刪除
    * */
    @Test
    public void delete() {
        int i = userMapper.deleteById(3);
        System.out.println(i);
    }
    /*
    * 批量刪除
    * */
    @Test
    public void delebatch() {
        int i = userMapper.deleteBatchIds(Arrays.asList(6, 5, 4));
        System.out.println(i);
    }
    /*
    * 根據條件刪除
    * */
    @Test
    public void deletiaojianb() {
        Map<String, Object> map  = new HashMap<>();
        map.put("name","wkm");
        int i = userMapper.deleteByMap(map);
        System.out.println(i);
    }
    @Test
    public void luojidelete() {
        Map<String, Object> map  = new HashMap<>();
        map.put("name","wkm");
        int i = userMapper.deleteByMap(map);
        System.out.println(i);
    }
    /*
    * 多個ID的批量查詢
    * */
    @Test
    public void getsome() {

        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4));
        System.out.println(users);
    }
/*
* 條件查詢
* */
@Test
public void getsomeone() {
    Map<String, Object> map = new HashMap<>();
    map.put("name","wkm");
    map.put("age",15);
    List<User> users = userMapper.selectByMap(map);
    System.out.println(users);
}
/*
* 上面不常用，常用條件構造器Wrapper複雜拆綫呢
* */
@Test
public void fuzachaxun() {
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.ge("age", 2);
    List<User> users = userMapper.selectList(wrapper);
    System.out.println(users);
}
    @Test
    public void fuzachaxun2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ne("age", 2);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }
    @Test
    public void fuzachaxun3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 0,100);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }
    @Test
    public void fuzachaxun4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "2");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }
    @Test
    public void fuzachaxun5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }
    /*
    * 分頁查詢
    * */
    @Test
    public void fenye() {
        Page<User> page = new Page<>(1,2);
        Page<User> userPage = userMapper.selectPage(page, null);
        System.out.println(userPage.getPages());//總頁數
        System.out.println(userPage.getCurrent());//當前頁
        System.out.println(userPage.getRecords());//數據集合
        System.out.println(userPage.getTotal());//總個數
        System.out.println(userPage.hasNext());//是否有下一個
        System.out.println(userPage.hasPrevious());//是否上一頁
    }
}
