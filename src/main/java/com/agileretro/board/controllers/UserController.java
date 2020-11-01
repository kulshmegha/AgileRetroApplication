package com.agileretro.board.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agileretro.board.domain.RetroBoard;
import com.agileretro.board.domain.User;
import com.agileretro.board.services.UserService;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value= "/users/register", produces = "application/json")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        User userByName = userService.findUserByName(user.getUsername());
        if(userByName != null) {
            return ResponseEntity.badRequest().build();
        }else{
            User addedUser = userService.save(user);
           URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                                          .path("/{user_name}").buildAndExpand(addedUser.getUsername()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @PostMapping(value = "/users/login/authenticate")
    public ResponseEntity<User> authenticateUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByName(authentication.getName());
       
        if(user ==  null) {
        	ResponseEntity.notFound().build();
        }
        System.out.print("==============User======================"+user);
        user.setPassword(null);
        return ResponseEntity.ok().body(user);
    }
    
    @GetMapping(value = "/users", produces = "application/json")
	public ResponseEntity<User[]> all(){
		List<User>  users =  userService.getUsers();
		User[] userArray =  users.toArray(new User[users.size()]);
		return ResponseEntity.ok(userArray);
	}
}
