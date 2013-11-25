package com.expenses.exception;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDoesNotExistException extends Throwable {
    String message;
    public UserDoesNotExistException(String memberEmailId) {
        message="User for email id "+memberEmailId+" does not exist";
    }

    @Override
    public String getMessage() {
        return super.getMessage() + message ;
    }
}
