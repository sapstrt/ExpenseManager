package com.expenses.exception;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/29/13
 * Time: 1:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class GroupExpenseException extends Throwable {
    private final String message;

    public GroupExpenseException(String s) {
        message=s;
    }

    @Override
    public String getMessage() {
        return super.getMessage()+message;
    }
}
