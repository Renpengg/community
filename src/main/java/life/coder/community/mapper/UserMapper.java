package life.coder.community.mapper;

import life.coder.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USER (ACCOUNT_ID, NAME, TOKEN, GMT_CREATE, GMT_MODIFIED, AVATAR_URL) VALUES (#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insert(User user);

    @Select("SELECT * FROM USER WHERE token = #{token}")
    User findByToken(@Param("token") String token);//这里findByToken是一个方法，接收token参数，返回一个user对象，方法就是注解中的select

    @Select("SELECT * FROM USER WHERE id = #{creator}")
    User findById(@Param("creator") Integer creator);

    @Select("SELECT * FROM USER WHERE account_id = #{accountId}")
    User findByAccountId(@Param(value = "accountId") String accountId);

    @Update("UPDATE USER SET NAME = #{name}, TOKEN = #{token}, GMT_MODIFIED = #{gmtModified}, AVATAR_URL = #{avatarUrl} WHERE id = #{id}")
    void update(User dbUser);
}
