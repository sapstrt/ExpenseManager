package com.expenses.service;

import com.expenses.domain.Group;
import com.expenses.exception.*;

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

    void activateMembership(int groupId, String memberEmailId) throws UserDoesNotExistException, GroupNotFoundException, UserActivationException, UserException;

    Group retrieveGroup(int groupId);

    Group retrieveGroup(String email, String groupName) throws UserDoesNotExistException, GroupNotFoundException, UserException;

    Group addMember(String ownerEmail, String groupName, String memberEmail, String memberName) throws UserDoesNotExistException, GroupNotFoundException, UserException;

    boolean activateMembership(String email, String groupName) throws GroupNotFoundException, UserDoesNotExistException, UserException;
}
