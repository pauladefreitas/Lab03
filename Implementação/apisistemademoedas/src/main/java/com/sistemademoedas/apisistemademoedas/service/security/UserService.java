package com.sistemademoedas.apisistemademoedas.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemademoedas.apisistemademoedas.model.security.Role;
import com.sistemademoedas.apisistemademoedas.model.security.User;
import com.sistemademoedas.apisistemademoedas.repository.security.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado. Email " + email));
    }

    @Transactional
    public User save(String email, String password) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);
        var user = new User(email, encryptedPassword, Role.CLIENT);
        return userRepository.save(user);
    }

    @Transactional
    public void updatePassword(String password, User user) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);
        user.setPassword(encryptedPassword);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado. Id " + userId));
    }
}
