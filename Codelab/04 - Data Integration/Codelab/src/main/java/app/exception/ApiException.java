package app.exception;

public class ApiException extends RuntimeException {

    //  Custom exception for API calls
    //  - Unchecked exception (runtime) no try-catch needed
    //  - RuntimeException -> Exception -> Throwable

    // Attributes
    private final int code;

    // ___________________________________________________
    // Message only

    public ApiException(String message) {
        super(message);
        this.code = 500;
    }

    // ___________________________________________________
    // Caused included | Advanced

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this.code = 500;
    }

    // ___________________________________________________
    // Specific error code

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
    }

    // ___________________________________________________
    // Full

    public ApiException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    // ___________________________________________________

    public int getCode(){
        return code;
    }

}