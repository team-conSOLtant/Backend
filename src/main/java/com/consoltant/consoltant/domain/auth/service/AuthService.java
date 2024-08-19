package com.consoltant.consoltant.domain.auth.service;

import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    public Long login(User user){
        userRepository.login();
        return null;
    }

    public Long logout(User user){
        userRepository.logout();
        return null;
    }

    public Long openAccountAuth(User user){

        return null;
    }

    public Long checkAuthCode(User user){
        return null;
    }
}
