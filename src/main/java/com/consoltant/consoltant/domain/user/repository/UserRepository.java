package com.consoltant.consoltant.domain.user.repository;

import com.consoltant.consoltant.domain.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.email LIKE %:email%")
    List<User> findByEmailLike(@Param("email") String email);

    @Query("SELECT CASE WHEN (u.role = 'COMPANY') THEN TRUE ELSE FALSE END FROM User u WHERE u.id = :id")
    Boolean isRoleCompany(@Param("id") Long id);
}
