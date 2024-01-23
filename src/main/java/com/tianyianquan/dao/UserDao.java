package com.tianyianquan.dao;

import com.tianyianquan.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserDao {
    @Select("select * from tbl_user where id= #{id}")
    UserDomain getById(Integer id);

    @Select("select * from tbl_user where username= #{username}")
    UserDomain getByName(String username);
}
