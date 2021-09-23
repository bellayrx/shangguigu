package com.atguigu.yygh.cmn.service;

import com.atguigu.yygh.model.cmn.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {
    //根据数据id查询子数据列表
    List<Dict> findChildData(Long id);

    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);

}
