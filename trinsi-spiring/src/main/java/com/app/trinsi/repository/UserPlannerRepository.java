package com.app.trinsi.repository;

import com.app.trinsi.model.UserPlanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPlannerRepository extends JpaRepository<UserPlanner, Long> {

}
