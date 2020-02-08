package life.coder.community.controller;

import life.coder.community.dto.CommentDTO;
import life.coder.community.dto.QuestionDTO;
import life.coder.community.enums.CommentTypeEnum;
import life.coder.community.service.CommentService;
import life.coder.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);

        //获取回复列表
        List<CommentDTO> commentDTO =  commentService.listByTargetId(id, CommentTypeEnum.QUESTION);

        //获取相关问题
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);

        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", commentDTO);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
