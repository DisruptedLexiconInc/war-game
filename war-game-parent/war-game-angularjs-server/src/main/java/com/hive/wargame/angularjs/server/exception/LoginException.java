package com.hive.wargame.angularjs.server.exception;

public class LoginException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -9118678094062713292L;

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable e) {
        super(message, e);
    }
}
