package com.scoreboard.match.service;

import com.scoreboard.match.config.ScoreboardUserDetails;
import com.scoreboard.match.entity.UserEntity;
import com.scoreboard.match.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class AuthenticationService implements UserDetailsService {

    private UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AtomicReference<UserDetails> userDetails=new AtomicReference<>();
        Optional<UserEntity> optional = userRepository.findById(userName);
        optional.ifPresentOrElse(user->{
            userDetails.set(new ScoreboardUserDetails(user));
        },()->{
            throw new UsernameNotFoundException("User not found");
        });
        return userDetails.get();
    }
}
