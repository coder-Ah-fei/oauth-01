package com.example.oauthserver.repository;

import com.example.oauthserver.entity.AuthorizationConsent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author yang
 */
public interface AuthorizationConsentRepository extends JpaRepository<AuthorizationConsent, Long>, JpaSpecificationExecutor<AuthorizationConsent> {

    void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);

     Optional<AuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);
}