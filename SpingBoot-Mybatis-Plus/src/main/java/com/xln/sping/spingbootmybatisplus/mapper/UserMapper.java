package com.xln.sping.spingbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xln.sping.spingbootmybatisplus.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;


// Mybatis3.4.0开始加入了@Mapper注解，从此就可不再写mapper映射文件（XML）
// @Mapper注解用于扫描并装载mapper接口类，Mybatis不推荐使用@Repository注解
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user ORDER BY age")
    List<User> getListUserSql();

    List<User> getListUserXml();

    // Map对象必须指定key字段
    @MapKey("id")
    Map<Long,User> getMapUserXml();

    @Select("SELECT * FROM user WHERE id=#{id}")
    User getOneUserByIDSql(Long id);

    User getOneUserByIDXml(Long id);
}
