package life.coder.community.enums;

public enum NotificationTypeEnum {
    REPLY_COMMENT(1, "回复了评论"),
    REPLY_QUESTION(2, "回复了问题")
    ;

    private int type;
    private String message;

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    NotificationTypeEnum(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public static String nameOfType(int type){
        for (NotificationTypeEnum value : NotificationTypeEnum.values()) {
            if(value.type == type){
                return value.getMessage();
            }
        }
        return "";
    }
}
