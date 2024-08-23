package com.consoltant.consoltant.domain.auth.service;

import com.consoltant.consoltant.domain.auth.dto.LoginRequestDto;
import com.consoltant.consoltant.domain.auth.dto.RegisterRequestDto;
import com.consoltant.consoltant.domain.university.repository.UniversityRepository;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.global.security.JwtUtil;
import com.consoltant.consoltant.util.api.RestTemplateUtil;
import lombok.RequiredArgsConstructor;
import com.consoltant.consoltant.domain.university.entity.University;


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
    private final RestTemplateUtil restTemplateUtil;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
<<<<<<< HEAD
=======
    private final AuthenticationManager authenticationManager;
    private final UniversityRepository universityRepository;

>>>>>>> 1ba4729826b890b04b844195b8388dab3e250483

    public String registerUser(RegisterRequestDto request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new BadRequestException("이미 존재하는 이메일 입니다.");
                });

        String encodePassword = passwordEncoder.encode(request.getPassword());
        String userKey = restTemplateUtil.createMember(request.getEmail());
        University university =universityRepository.findById(1L).orElseThrow(()->new BadRequestException("SQL Error"));

        return userRepository.save(request.createUser(encodePassword,userKey,university)).getEmail();
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
