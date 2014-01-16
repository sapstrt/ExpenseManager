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
public class MemberProps{
    public static MemberProps ADMIN=new MemberProps(true,true);
    public static MemberProps ACTIVE=new MemberProps(true,false);
    public static MemberProps PASSIVE=new MemberProps(false,false);

    private boolean isActive;
    private boolean isAdmin;

    MemberProps() {

    }

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

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
