package com.scoreboard.match.controller;

import com.scoreboard.match.controller.request.UserRequest;
import com.scoreboard.match.entity.UserEntity;
import com.scoreboard.match.exception.UserAlreadyExistException;
import com.scoreboard.match.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(path="/add",consumes = "application/json",produces = "application/json")
    public ResponseEntity<? extends Object> addUser(@RequestBody UserRequest request){
        try {
            UserEntity entity= userService.addUser(request);
            if(entity!=null)
                return new ResponseEntity<UserEntity>(entity,HttpStatus.OK);
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
//Put->Update-Except user name
//Get->userName-Details
//Delete->userName->Delete user-enable-0