package life.coder.community.service;

import life.coder.community.enums.CommentTypeEnum;
import life.coder.community.exception.CustomizeErrorCode;
import life.coder.community.exception.CustomizeException;
import life.coder.community.mapper.CommentMapper;
import life.coder.community.mapper.QuestionExtMapper;
import life.coder.community.mapper.QuestionMapper;
import life.coder.community.model.Comment;
import life.coder.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.Type_PARAM_NOT_FOUND);
        }

        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else{
            //回复问题
            Question dbQuestion = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(dbQuestion == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            dbQuestion.setCommentCount(1);
            questionExtMapper.incCommentCount(dbQuestion);
        }
    }
}
