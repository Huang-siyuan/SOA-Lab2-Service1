package com.itmo.soa.exceptions;

/**
 * @program: SOA-Lab2
 * @author: Siyuan
 * @create: 2022-10-11 18:58
 **/
public class ValidationException extends ServiceException {
    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    protected ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
