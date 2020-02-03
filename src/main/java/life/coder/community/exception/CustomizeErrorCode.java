package life.coder.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你找的问题不在了，要不换个试试"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或者回复"),
    NO_LOGIN(2003, "你还没有登录呢，请先进行登录"),
    SYS_ERROR(2004, "服务器冒烟了，要不你等会再试试"),
    Type_PARAM_NOT_FOUND(2005, "评论类型错误"),
    COMMENT_NOT_FOUND(2006, "这个评论找不到了"),
    ;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() { return code;}

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
