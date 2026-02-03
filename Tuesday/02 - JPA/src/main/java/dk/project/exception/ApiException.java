package dk.project.exception;

public class ApiException extends RuntimeException {

    // Attributes
    private int code;

    // _________________________________________________

    public ApiException(int code, String msg){
        super(msg);
        this.code = code;
    }

    // _________________________________________________

    public int getCode(){
        return code;
    }

}