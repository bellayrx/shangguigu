package com.atguigu.yygh.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.yygh.cmn.Listener.DictListener;
import com.atguigu.yygh.cmn.mapper.DictMapper;
import com.atguigu.yygh.cmn.service.DictService;
import com.atguigu.yygh.model.cmn.Dict;
import com.atguigu.yygh.vo.cmn.DictEeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.Cacheable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    @Cacheable(value ="dict")
    public List<Dict> findChildData(Long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",id);
        List<Dict> list = baseMapper.selectList(queryWrapper);
        //向list每个dict中haschildren设置相关值得
        for(Dict dict: list){
            long dictIdid =dict.getId();
            boolean isChild = this.isChildern(dictIdid);
            dict.setHasChildren(isChild);
        }
        return list;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");//类型
            response.setCharacterEncoding("utf-8");//编码
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName ="dict";
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
            //查询数据库
            List<Dict> dictList = baseMapper.selectList(null);
            List<DictEeVo> dictVoList = new ArrayList<>(dictList.size());
            for(Dict dict : dictList) {
                DictEeVo dictVo = new DictEeVo();
                BeanUtils.copyProperties(dict, dictVo, DictEeVo.class);
                dictVoList.add(dictVo);
            }

            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("数据字典").doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @CacheEvict(value = "dict", allEntries=true)
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //判断Id下是否有子节点
    private boolean isChildern(long id){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",id);
        Integer count=baseMapper.selectCount(queryWrapper);
        //count>0 返回false
        return count>0;//直接返回falses
    }
}
