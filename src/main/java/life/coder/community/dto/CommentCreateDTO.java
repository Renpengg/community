package life.coder.community.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private long parentId;
    private int type;
    private String content;
}
