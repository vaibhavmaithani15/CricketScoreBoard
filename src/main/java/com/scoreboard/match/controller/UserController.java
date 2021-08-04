package com.scoreboard.match.controller;

import com.scoreboard.match.controller.request.UserRequest;
import com.scoreboard.match.entity.UserEntity;
import com.scoreboard.match.exception.UserAlreadyExistException;
import com.scoreboard.match.exception.UserNotFoundException;
import com.scoreboard.match.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "*")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //POST MAPPING
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<? extends Object> addUser(@RequestBody UserRequest request) {
        try {
            UserEntity entity = userService.addUser(request);
            if (entity != null)
                return new ResponseEntity<UserEntity>(entity, HttpStatus.OK);
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //GET MAPPING WITH ALL USER
    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<UserEntity> getAllUser() {
        try {
            List<UserEntity> allUser = userService.getAllUser();
            if (!allUser.isEmpty()) {
                return new ResponseEntity(allUser, HttpStatus.OK);
            }
        } catch (UserNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // GET MAPPING WITH SINGLE USER
    @GetMapping(path = "/get/{userName}", produces = "application/json")
    public ResponseEntity<UserEntity> getUser(@PathVariable String userName) {
        try {
            UserEntity user = userService.getUser(userName);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        } catch (UserNotFoundException e) {
            return new ResponseEntity(new UserNotFoundException("User Not exist in our system"), HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // DELETE MAPPING WITH SINGLE USER
    @DeleteMapping(path = "/delete/{userName}", produces = "application/json")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable String userName) {
        try {
            UserEntity deletedUser = userService.deleteUser(userName);
            if (deletedUser != null) {
                return new ResponseEntity<UserEntity>(HttpStatus.OK);
            }
        } catch (UserNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //UPDATE MAPPING WITH SINGLE USER
    @PutMapping(path = "/update/{userName}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserEntity> updateUser(@PathVariable String userName, @RequestBody UserRequest request) {
        try {
            UserEntity updatedUser = userService.updateUser(userName, request);
            if (updatedUser != null) {
                return new ResponseEntity(updatedUser,HttpStatus.OK);
            }
        } catch (UserNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

// User password hide only in GET Request DONE
// Team Name -> primary key DONE
// GET DELETE PUT ->Team DONE
// Error Response in JSON Format
//Player Entity
//Match Entity