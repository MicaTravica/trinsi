package com.app.trinsi.repository;

import com.app.trinsi.model.UserHealth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserHealthRepository extends JpaRepository<UserHealth, Long> {

    @Query("select uh from UserHealth uh join User u where u.id = ?1")
    Optional<UserHealth> findByUsername(Long id);
}
