package com.expenses.exception;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 10:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupCreationException extends Throwable {
    String message;
    public GroupCreationException(String s) {
        message=s;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + message;
    }
}
