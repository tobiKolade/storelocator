package com.jumbo.storelocator.exception;

/**
 * Created by tobi.oladimeji on 09/12/2019
 */
public abstract class BaseException extends RuntimeException implements I18n {

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}
