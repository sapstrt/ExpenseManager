package com.expenses.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/24/13
 * Time: 7:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Embeddable
public class ExpenseDetails {
    @Embedded
    private Location location;
    private String description;
    private Date timestamp;

    public ExpenseDetails() {
        timestamp=new Date();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
