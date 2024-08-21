package com.consoltant.consoltant.domain.user.repository;

import com.consoltant.consoltant.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModuleRepository extends JpaRepository<User,Long> {
}
