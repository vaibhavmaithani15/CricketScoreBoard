package com.scoreboard.match.service;

import com.scoreboard.match.controller.request.UserRequest;
import com.scoreboard.match.entity.UserEntity;
import com.scoreboard.match.exception.UserAlreadyExistException;
import com.scoreboard.match.exception.UserNotFoundException;
import com.scoreboard.match.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    // ADD USER SERVICE
    public UserEntity addUser(UserRequest request) throws UserAlreadyExistException {

        Optional<UserEntity> optionalUserEntity = repository.findById(request.userName);
        if (optionalUserEntity.isPresent()) {
            throw new UserAlreadyExistException("User already exist in our system please select another user name");
        } else {
            if(request.getPassword().isEmpty()){
                throw new UserAlreadyExistException("Password cannot be empty");
            }else {
                UserEntity entity = UserEntity.builder()
                        .userName(request.userName)
                        .password(bCryptPasswordEncoder.encode(request.password))
                        .firstName(request.firstName)
                        .lastName(request.lastName)
                        .enabled(1)
                        .userCreatedBy("System")
                        .role("user")
                        .build();
                return repository.save(entity);
            }
        }
    }


    // DELETE USER SERVICE
    public UserEntity deleteUser(String userName) throws UserNotFoundException {
        Optional<UserEntity> optionalUserEntity = repository.findById(userName);

        if (optionalUserEntity.isPresent()) {
            UserEntity entity = UserEntity.builder().
                    userName(userName).
                    firstName(optionalUserEntity.get().getFirstName())
                    .lastName(optionalUserEntity.get().getLastName())
                    .password(optionalUserEntity.get().getPassword())
                    .enabled(0)
                    .userCreatedBy(optionalUserEntity.get().getUserCreatedBy())
                    .role(optionalUserEntity.get().getRole())
                    .build();
            return repository.save(entity);
        } else {
            throw new UserNotFoundException("User not exists in our system");

        }


    }


    // UPDATE USER SERVICE
    public UserEntity updateUser(String userName, UserRequest request) throws UserNotFoundException {
        Optional<UserEntity> optionalUserEntity = repository.findById(userName);
        if (optionalUserEntity.isPresent()) {
            UserEntity entity = UserEntity.builder()
                    .userName(userName)
                    .password(optionalUserEntity.get().getPassword())
                    .firstName(request.firstName)
                    .lastName(request.lastName)
                    .enabled(request.enabled)
                    .userCreatedBy("System")
                    .role("user")
                    .build();

            return repository.save(entity);
        } else {
            throw new UserNotFoundException("User not exists in our system");

        }
    }


    // GET SINGLE USER SERVICE
    public UserEntity getUser(String userName) throws UserNotFoundException {
        Optional<UserEntity> optionalUserEntity = repository.findById(userName);
        UserEntity entity = new UserEntity();
        if (optionalUserEntity.isPresent()) {
            entity.setUserName(optionalUserEntity.get().getUserName());
            entity.setFirstName(optionalUserEntity.get().getFirstName());
            entity.setLastName(optionalUserEntity.get().getLastName());
            entity.setUserCreatedBy(optionalUserEntity.get().getUserCreatedBy());
            entity.setRole(optionalUserEntity.get().getRole());
            entity.setEnabled(optionalUserEntity.get().getEnabled());
            return entity;
        } else {
            throw new UserNotFoundException("User not exists in our system");
        }
    }


    // GET ALL USER SERVICE
    public List<UserEntity> getAllUser() throws UserNotFoundException {
        List<UserEntity> users = repository.findAll();
        for (UserEntity user : users) {
            user.setPassword(null);
//            users.add(user);
        }

        if (users.isEmpty()) {
            throw new UserNotFoundException("User not exists in our system");
        } else {
            return users;
        }
    }
}
