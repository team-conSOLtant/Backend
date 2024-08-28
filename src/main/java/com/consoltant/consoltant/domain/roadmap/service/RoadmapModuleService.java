package com.consoltant.consoltant.domain.roadmap.service;

import com.consoltant.consoltant.domain.roadmap.entity.Roadmap;
import com.consoltant.consoltant.domain.roadmap.repository.RoadmapRepository;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.domain.user.service.UserService;
import com.consoltant.consoltant.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoadmapModuleService {
    private final RoadmapRepository roadmapRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public Long findRoadmapUserId(Long userId){
        Roadmap roadmap = roadmapRepository.findById(userId).orElse(null);

        return (roadmap==null?null:roadmap.getId());
    }

    @Transactional
    public void saveRoadmap(Long userId, Long roadmapId){
        User user = userRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다"));
        User roadmapUser = userRepository.findById(roadmapId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다"));

        Roadmap roadmap = Roadmap.builder()
                .user(user)
                .roadmapUser(roadmapUser)
                .build();

        roadmapRepository.save(roadmap);
    }
}
