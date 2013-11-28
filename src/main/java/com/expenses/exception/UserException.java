package com.expenses.exception;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/29/13
 * Time: 1:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserException extends Throwable {
    private String message;
    public UserException(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return super.getMessage()+message;
    }
}
