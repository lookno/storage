package cn.neu.exception;

public class ServerException extends Exception {
    private static final long serialVersionUID = 1L;
    private String code;
    public ServerException(String message) {
        super(message);
    }

    public ServerException(String code, String message) {
        this(message);
        setCode(code);
    }
    
    public ServerException(ExceptionCode code) {
        this(code.getMessage());
        setCode(code.getCode());
    }

    public ServerException(String code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
    }
    
    public ServerException(ExceptionCode code, Throwable cause) {
        super(code.getMessage(), cause);
        setCode(code.getCode());
    }

    public ServerException(String code, Throwable cause) {
        super(cause);
        setCode(code);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
