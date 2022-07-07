package com.example.oauthserver.repository;

import com.example.oauthserver.entity.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author yang
 */
public interface AuthorizationRepository extends JpaRepository<Authorization, String>, JpaSpecificationExecutor<Authorization> {

    Optional<Authorization> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValue(String token, String t1,String t2, String t3);

    Optional<Authorization> findByState(String token);

    Optional<Authorization> findByAuthorizationCodeValue(String token);

    Optional<Authorization> findByAccessTokenValue(String token);

    Optional<Authorization> findByRefreshTokenValue(String token);
}