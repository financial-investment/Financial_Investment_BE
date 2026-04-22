package com.financialinvestment.domain.auth.repository;

import com.financialinvestment.domain.auth.entity.OauthProvider;
import com.financialinvestment.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderAndProviderUserId(OauthProvider oauthProvider, String providerUserId);
}

