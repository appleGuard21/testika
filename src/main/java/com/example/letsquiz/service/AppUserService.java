package com.example.letsquiz.service;

import com.example.letsquiz.entity.user.AppUser;
import com.example.letsquiz.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository userRepository;

    @Autowired
    public AppUserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findAppUserByUsername(username).orElseThrow(()->
                new UsernameNotFoundException(String.format("User %s is not found", username)));
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(AppUser appUser){
        userRepository.save(appUser);
    }

    public AppUser findUserByUsername(String username){
        return userRepository.findAppUserByUsername(username).orElseThrow(()->
                new UsernameNotFoundException(String.format("User %s is not found", username)));
    }
    public AppUser findUserById(long id){
        return userRepository.findAppUserById(id).orElseThrow(()->
                new UsernameNotFoundException(String.format("User %s is not found", id)));
    }
}
