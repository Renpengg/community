package life.coder.community.controller;

import life.coder.community.dto.NotificationDTO;
import life.coder.community.enums.NotificationTypeEnum;
import life.coder.community.mapper.NotificationMapper;
import life.coder.community.model.User;
import life.coder.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name = "id") Long id, HttpServletRequest request){

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }

        NotificationDTO notificationDTO = notificationService.read(user, id);
        if(notificationDTO.getType() == NotificationTypeEnum.REPLY_COMMENT.getType()
        || notificationDTO.getType() == NotificationTypeEnum.REPLY_QUESTION.getType()){
            return "redirect:/question/" + notificationDTO.getOuterid();
        }else{
            return "redirect:/";
        }
    }
}
