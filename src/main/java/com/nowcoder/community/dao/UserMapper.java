package com.nowcoder.community.dao;

// 访问数据库需要在DAO中写一个组件，Mybatis中的数据访问组件为Mapper，而且只需要写接口，不用写实现类
// 需要注解，才能让Spring装备接口为Bean

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 方法按照业务要求写

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    // 插入用户，返回的是整数，代表插入的行数，传参为User
    int insertUser(User user);

    // 修改用户状态，返回的是条数（返回几行数据）以id、status为参数
    int updateStatus(int id, int status);

    // 更新头像，以id、headerUrl为参数
    int updateHeader(int id, String headerUrl);

    // 更新密码，以id、password为参数
    int updatePassword(int id, String password);

}

// 实现这些方法 需要提供配置文件 配置文件给这些方法提供SQL Mybatis会自动生成实现类
// 配置文件在 /resources/mapper/user-mapper.xml