package app.exception;

public class ResourceNotFoundException extends RuntimeException {

    //  Custom exception for missing Resource calls
    //  - Unchecked exception (runtime) no try-catch needed
    //  - RuntimeException -> Exception -> Throwable

    // Attributes
    private final String entity;
    private final String entityValue;

    // ___________________________________________________
    // Message only

    public ResourceNotFoundException(String entity, String entityValue) {
        super(entity + " not found: " + entityValue);
        this.entity = entity;
        this.entityValue = entityValue;
    }

    // ___________________________________________________
    // Cause included | Advanced

    public ResourceNotFoundException(String entity, String entityValue, Throwable cause) {
        super(entity + " not found: " + entityValue, cause);
        this.entity = entity;
        this.entityValue = entityValue;
    }

    // ___________________________________________________

    public String getEntity() {
        return entity;
    }

    // ___________________________________________________

    public String getEntityValue() {
        return entityValue;
    }

}