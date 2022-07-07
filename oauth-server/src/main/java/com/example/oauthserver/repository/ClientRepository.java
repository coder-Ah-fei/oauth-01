package com.example.oauthserver.repository;

import com.example.oauthserver.entity.Authorization;
import com.example.oauthserver.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.Optional;

/**
 * @author yang
 */
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    Optional<Client> findByClientId(String clientId);
}