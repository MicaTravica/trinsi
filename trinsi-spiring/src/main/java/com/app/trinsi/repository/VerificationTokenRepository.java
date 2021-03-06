package com.app.trinsi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.trinsi.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

	Optional<VerificationToken> findByToken(String token);

}
