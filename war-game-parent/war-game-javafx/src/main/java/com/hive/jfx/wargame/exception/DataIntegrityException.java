package com.hive.jfx.wargame.exception;

public class DataIntegrityException extends RemoteServiceException {

    /**
     *
     */
    private static final long serialVersionUID = -1005414447303925400L;

    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }
}
