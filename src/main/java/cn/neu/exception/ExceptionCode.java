package cn.neu.exception;

public enum ExceptionCode {
    NO_PERMISSION("NO_PERMISSION","没有权限");
    
    private String code;
    private String message;
    private ExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    
}
