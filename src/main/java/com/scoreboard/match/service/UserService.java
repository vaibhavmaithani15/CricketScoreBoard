package com.scoreboard.match.service;

import com.scoreboard.match.controller.request.UserRequest;
import com.scoreboard.match.entity.UserEntity;
import com.scoreboard.match.exception.UserAlreadyExistException;
import com.scoreboard.match.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity addUser(UserRequest request) throws UserAlreadyExistException {
        Optional<UserEntity> optionalUserEntity = repository.findById(request.userName);
        if(optionalUserEntity.isPresent()){
            throw new UserAlreadyExistException("User already exist in our system please select another user name");
        }else {
            UserEntity entity = UserEntity.builder()
                    .userName(request.userName)
                    .firstName(request.firstName)
                    .lastName(request.lastName)
                    .password(request.password)
                    .enabled(1)
                    .userCreatedBy("System")
                    .role("user")
                    .build();
            return repository.save(entity);
        }
    }
}
