package cn.neu.exception;

public class ServiceException extends Exception {

    private static final long serialVersionUID = 1L;
    public String code;
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        this(message);
        setCode(code);
    }
    
    public ServiceException(ExceptionCode code) {
        this(code.getMessage());
        setCode(code.getCode());
    }

    public ServiceException(String code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
    }
    
    public ServiceException(ExceptionCode code, Throwable cause) {
        super(code.getMessage(), cause);
        setCode(code.getCode());
    }

    public ServiceException(String code, Throwable cause) {
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
