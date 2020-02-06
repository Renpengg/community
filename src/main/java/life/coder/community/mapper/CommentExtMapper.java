package life.coder.community.mapper;

import life.coder.community.model.Comment;
import life.coder.community.model.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}