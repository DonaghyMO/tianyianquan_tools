package com.tianyianquan.dao;

import com.tianyianquan.bean.ResultBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface ExecuteDao{
    @Insert("insert into tbl_result (data,command,status) values(#{data},#{command},#{status})")
    void save(ResultBean result);


    @Delete("delete from tbl_result where id = #{id}")
    public int delete(Integer id);

    @Select("select * from tbl_result where id = #{id}")
    ResultBean getById(Integer id);

    @Select("select * from tbl_result")
    List<ResultBean> getAll();
}

