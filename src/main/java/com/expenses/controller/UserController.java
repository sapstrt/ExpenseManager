package com.expenses.controller;

import com.expenses.domain.User;
import com.expenses.exception.UserException;
import com.expenses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 1/26/14
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/user")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "new",method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<User> createNewUser(@RequestBody User user){
        System.out.println(user);
        if (user.getUserName()==null || user.getEmail()==null)
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        user=userService.createNewUser(user.getUserName(),user.getEmail());
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody User getUserByEmail(@RequestParam("email")String email){
        User user=userService.retrieveUser(email);
        System.out.println(user);
        return user;
    }

    @RequestMapping(value = "/activate",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<String> activateUser(@RequestParam("email") String email){
        User user=userService.retrieveUser(email);
        if (user==null)
            return new ResponseEntity<String>("User does not exist",HttpStatus.BAD_REQUEST);
        return userService.activateUser(user)?new ResponseEntity<String>("Activated",HttpStatus.OK):new ResponseEntity<String>("NOt Activated",HttpStatus.BAD_REQUEST);
    }

}
