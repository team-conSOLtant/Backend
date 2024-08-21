package com.consoltant.consoltant.domain.auth.service;

import com.consoltant.consoltant.domain.auth.dto.LoginRequestDto;
import com.consoltant.consoltant.domain.auth.dto.RegisterRequestDto;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.global.security.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String registerUser(RegisterRequestDto request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new BadRequestException("이미 존재하는 이메일 입니다.");
                });

        return userRepository.save(request.createUser(passwordEncoder.encode(request.getPassword()))).getEmail();
    }

    public Long logout(User user){
//        userRepository.logout();
        return null;
    }

    public Long openAccountAuth(User user) {
        return null;
    }

    public Long checkAuthCode(User user){
        return null;
    }

}
