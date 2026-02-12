package dk.project.exception;

public class ApiException extends RuntimeException {

    // Attributes
    private int code;

    // _____________________________________________________

    public ApiException(int code, String msg){
        super(msg);
        this.code = code;
    }

    // _____________________________________________________

    public int getCode(){
        return code;
    }

}