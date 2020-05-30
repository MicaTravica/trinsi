package com.app.trinsi.repository;

import com.app.trinsi.model.UserPlanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPlannerRepository extends JpaRepository<UserPlanner, Long> {

}