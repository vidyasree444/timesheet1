package org.imaginnovate.Exception;

public class DivisionServiceException extends RuntimeException {
    private int statusCode;

    public DivisionServiceException(String message) {
        super(message);
    }

    public DivisionServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DivisionServiceException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
