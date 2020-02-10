package life.coder.community.enums;

public enum NotificationStatusEnum {
    UNREAD(0,"消息未读"),
    READ(1, "消息已读")
    ;

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    NotificationStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
