package com.hive.jfx.wargame.exception;

public class RemoteServiceException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 2966784385516925602L;

    public RemoteServiceException(String message) {
        super(message);
    }

    public RemoteServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteServiceException(Throwable cause) {
        super(cause);
    }
}
