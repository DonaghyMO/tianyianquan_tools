package com.tianyianquan.dao;

import com.tianyianquan.model.ExecuteDomain;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ExecuteDao{
    @Insert("insert into tbl_result (data,command,status) values(#{data},#{command},#{status})")
    void save(ExecuteDomain result);


    @Delete("delete from tbl_result where id = #{id}")
    public int delete(Integer id);

    @Select("select * from tbl_result where id = #{id}")
    ExecuteDomain getById(Integer id);

    @Select("select * from tbl_result where only_id = #{onlyId}")
    ExecuteDomain getByOnlyId(String onlyId);

    @Select("select * from tbl_result")
    List<ExecuteDomain> getAll();

    @Insert("insert into tbl_result (command,status,only_id) values(#{command},#{status},#{onlyId})")
    void saveWithOnlyId(ExecuteDomain domain);
}

