package com.example.page.Mapper;


import com.example.page.Entity.User;
import com.example.page.Result.Pager;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserMapper {

    public List<User> findByPager(Map<String, Object> params);
    public Page<User> findall();
    public long count();
}
