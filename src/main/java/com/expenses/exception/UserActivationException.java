package com.expenses.exception;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserActivationException extends Throwable {
    String message;
    public UserActivationException(String memberEmailId,String groupName) {
        message=" user activation failed for email id " +memberEmailId + " for group " + groupName;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + message;
    }
}
