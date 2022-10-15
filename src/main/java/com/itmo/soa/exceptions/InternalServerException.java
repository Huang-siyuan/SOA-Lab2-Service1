package com.itmo.soa.exceptions;

/**
 * @program: SOA-Lab2
 * @description:
 * @author: Siyuan
 * @create: 2022-10-11 18:59
 **/
public class InternalServerException extends ServiceException {
    public InternalServerException() {
        super();
    }

    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerException(Throwable cause) {
        super(cause);
    }

    protected InternalServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

