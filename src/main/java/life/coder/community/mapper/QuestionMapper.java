package life.coder.community.mapper;


import life.coder.community.dto.QuestionDTO;
import life.coder.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {


    @Insert("INSERT INTO question (title, description, gmt_create, gmt_modified, creator, tag) VALUES(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("SELECT * FROM question limit #{offset}, #{size}")
    List<Question> list(@Param(value = "offset") Integer page, @Param(value = "size") Integer size);

    @Select("SELECT count(1) FROM question")
    Integer count();

    @Select("SELECT * FROM question WHERE creator = #{id} limit #{offset}, #{size}")
    List<Question> listById(@Param(value = "id") Integer id,@Param(value = "offset") Integer page, @Param(value = "size") Integer size);

    @Select("SELECT count(1) FROM question WHERE creator = #{id}")
    Integer countByUserId(@Param(value = "id") Integer id);

    @Select("SELECT * FROM question WHERE id = #{id}")
    Question getById(@Param(value = "id") Integer id);
}
