package com.expenses.domain;

import javax.persistence.Embeddable;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 12/4/13
 * Time: 12:28 AM
 * To change this template use File | Settings | File Templates.
 */
@Embeddable
public enum MemberProps{
    ADMIN(true,true),ACTIVE(true,false),PASSIVE(false,false);
    private final boolean isActive;
    private final boolean isAdmin;

    MemberProps(boolean isActive, boolean isAdmin) {
        this.isActive=isActive;
        this.isAdmin=isAdmin;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
