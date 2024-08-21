package com.consoltant.consoltant.domain.projectuser.repository;

import com.consoltant.consoltant.domain.projectuser.entity.ProjectUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {
    List<ProjectUser> findAllByProjectId(Long id);
}
