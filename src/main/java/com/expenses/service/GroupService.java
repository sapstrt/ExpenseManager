package com.expenses.service;

import com.expenses.domain.Group;
import com.expenses.exception.GroupCreationException;
import com.expenses.exception.GroupNotFoundException;
import com.expenses.exception.UserActivationException;
import com.expenses.exception.UserDoesNotExistException;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 12:52 AM
 * To change this template use File | Settings | File Templates.
 */
public interface GroupService {
    Group createGroup(String groupName, String ownerEmailId) throws GroupCreationException;

    void addMember(int groupId, String memberEmailId) throws UserDoesNotExistException, GroupNotFoundException;

    void activateMembership(int groupId, String memberEmailId) throws UserDoesNotExistException, GroupNotFoundException, UserActivationException;

    Group retrieveGroup(int groupId);

}
