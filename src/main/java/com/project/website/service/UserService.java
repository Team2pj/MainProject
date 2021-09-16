package com.project.website.service;

import com.project.website.domain.entity.Role;

import com.project.website.domain.repository.UserRepository;
import com.project.website.domain.entity.UserEntity;
import com.project.website.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.aop.ClassFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Transactional
    public Long join(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(userDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);

        List<GrantedAuthority> auth = new ArrayList<>();

        if(("admin@example.com").equals(email)) {
            auth.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            auth.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }

        return new User(user.getEmail(), user.getPassword(), auth);
    }

}
