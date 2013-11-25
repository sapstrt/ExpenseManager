package com.expenses.exception;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 11:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupNotFoundException extends Throwable {
    String message;
    public GroupNotFoundException(int groupId) {
        message="group not found for id "+groupId;
    }

    @Override
    public String getMessage() {
        return super.getMessage()+message;
    }
}
