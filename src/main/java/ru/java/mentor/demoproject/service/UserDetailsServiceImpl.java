package ru.java.mentor.demoproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.java.mentor.demoproject.domain.User;
import ru.java.mentor.demoproject.repositories.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User byLogin = userRepository.findByLogin(login);

        if (byLogin==null) {
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        }

        return byLogin;
    }

}
