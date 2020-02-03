package life.coder.community.controller;

import life.coder.community.dto.CommentDTO;
import life.coder.community.dto.ResultDTO;
import life.coder.community.exception.CustomizeErrorCode;
import life.coder.community.exception.CustomizeException;
import life.coder.community.mapper.CommentMapper;
import life.coder.community.mapper.QuestionMapper;
import life.coder.community.model.Comment;
import life.coder.community.model.User;
import life.coder.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setCommentator(user.getId());
        comment.setParentId(commentDTO.getParentId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setType(commentDTO.getType());
        comment.setLikeCount(0l);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
