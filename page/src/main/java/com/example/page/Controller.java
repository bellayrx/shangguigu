package com.example.page;


import com.example.page.Entity.User;
import com.example.page.Mapper.UserMapper;
import com.example.page.Result.Pager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {
    @Autowired
    private UserMapper service;
    @GetMapping("/")
    public String findByPager(){
        int page =1;
        int size=1;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", (page-1)*size);
        params.put("size", size);
        Pager<User> pager = new Pager<User>();
        List<User> list = service.findByPager(params);
        pager.setRows(list);
        pager.setTotal(service.count());
        System.out.println(pager);
        return pager.toString();
    }
/*        @GetMapping("/1")
    public Pager<User> findByPagers(){
*//*            int page =1;
            int size=10;
        Page<User> res = PageHelper.startPage(page,size);
        List<Pager<User>> pagers = service.findall();
        System.out.println(pagers);
        System.out.println(res);
        return res;*//*
            return "1";
    }*/
    @RequestMapping("/2")
    public Pager<User> getUserList(Integer pageNum, Integer pageSize){
        Pager<User> pager = new Pager<>();
        Page<User> res = PageHelper.startPage(pageNum, pageSize);
        service.findall();
        pager.setRows(res.getResult());
        pager.setTotal(res.getTotal());
        pager.setPage(pageNum);
        pager.setSize(pageSize);
        return pager;
    }

    public static void main(String[] args) {
        int i=0;
        i=i++;
        System.out.println(i);
    }
}
