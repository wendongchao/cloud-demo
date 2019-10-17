package cn.itcast.user.mapper;

import cn.itcast.user.pojo.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * 使用mapper通用组件，可以实现单表的CRUD操作，很方便，
 * 涉及多表的操作可以使用xml文件或者mapper方法注释
 */
public interface UserMapper extends Mapper<User> {
}
