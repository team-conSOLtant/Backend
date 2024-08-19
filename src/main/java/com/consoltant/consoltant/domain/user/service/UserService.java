package com.consoltant.consoltant.domain.user.service;

import com.consoltant.consoltant.domain.user.dto.CreateUserResponseDto;
import com.consoltant.consoltant.domain.user.dto.UpdateUserResponseDto;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.mapper.UserMapper;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor;

    public User getUser(Long id)  {
        return userRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));
    }

    public CreateUserResponseDto createUser(User user){
        //User Key 생성하기

        // 타임아웃 설정
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // 타임아웃 설정 5초

        // RestTemplate 객체 생성
        RestTemplate restTemplate = new RestTemplate(factory);


        HttpHeaders headers = new HttpHeaders();
        headers.set("apiName", "openAccountAuth");
        headers.set("transmissionDate", new Date().toString());
        headers.set("transmissionTime", new Date().toString());
        headers.set("institutionCode", "00100");
        headers.set("fintechAppNo", "001");
        headers.set("apiServicecode", "openAccountAuth");
        headers.set("apiKey", "001");
        headers.set("fintechAppNo", "001");

        headers.set("fintechAppNo", "001");


        출처: https://juntcom.tistory.com/141 [쏘니의 개발블로그:티스토리]

        // 요청 URL 및 쿼리스트링 설정
        URI url = UriComponentsBuilder.fromHttpUrl("https://finopenapi.ssafy.io/ssafy/api/1/edu/accountAuth/openAccountAuth")
                .queryParams("파라미터명", "")
                .queryParams("파라미터명", "")
                .queryParams("파라미터명", URLEncoder.encode("", "UTF-8"))
                .build();


        // HTTP GET 요청
        ResponseEntity<String> response = restTemplate.exchange(url.toString(), HttpMethod.GET, entity, String.class);

        // HTTP GET 요청에 대한 응답 확인
        System.out.println("status : " + response.getStatusCode());
        System.out.println("body : " + response.getBody());


        userRepository.save(user);

        return userMapper.toCreateUserResponseDto(user);
    }

    @Transactional
    public UpdateUserResponseDto updateUser(Long id, User user){
        User entity = userRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다.")) ;

        entity.changeOf(user);

        return userMapper.toUpdateUserResponseDto(entity);
    }

    public Long deleteUser(Long id){
        userRepository.deleteById(id);

        return id;
    }

}
