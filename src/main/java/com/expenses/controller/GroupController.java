package com.expenses.controller;

import com.expenses.domain.Group;
import com.expenses.domain.User;
import com.expenses.exception.GroupNotFoundException;
import com.expenses.exception.UserDoesNotExistException;
import com.expenses.exception.UserException;
import com.expenses.service.GroupService;
import com.expenses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 1/30/14
 * Time: 1:05 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequestMapping("/group")
public class GroupController {

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public @ResponseBody Group createNewGroup(@RequestParam("email") String email, @RequestBody Group group) throws UserException {
        User user= userService.retrieveUser(email);
        if (user==null || !user.isActive())
            throw new UserException("User does not exist or is not active");
        group=new Group(group.getGroupName(),user);
        return  group;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Group retrieveGroup(@RequestParam("email") String email,@RequestParam("name") String groupName) throws UserDoesNotExistException, GroupNotFoundException, UserException {
        Group group=groupService.retrieveGroup(email,groupName);
        return group;
    }

    @RequestMapping(value = "/member/add",method = RequestMethod.POST)
    public @ResponseBody Group addMembersToGroup(@RequestParam("email") String email, @RequestParam("name") String groupName,@RequestBody User user) throws GroupNotFoundException, UserDoesNotExistException, UserException {
        Group group=groupService.addMember(email,groupName,user.getEmail(),user.getUserName());
        return group;
    }

    @RequestMapping(value ="/member/activate",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> activateMembership(@RequestParam("email")String email,@RequestParam("name") String groupName) throws GroupNotFoundException, UserDoesNotExistException, UserException {
        if (groupService.activateMembership(email,groupName)){
            return new ResponseEntity<String>("Activated", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Invalid Request",HttpStatus.BAD_REQUEST);
        }
    }
}
