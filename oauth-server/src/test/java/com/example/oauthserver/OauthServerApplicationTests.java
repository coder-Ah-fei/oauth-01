package com.example.oauthserver;

import com.example.oauthserver.entity.Client;
import com.example.oauthserver.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.time.Instant;
@SpringBootTest
class OauthServerApplicationTests {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void contextLoads() {
        Client client = new Client();
        client.setId(1L);
        client.setClientId("messaging-client");
        client.setClientIdIssuedAt(Instant.ofEpochSecond(10000L));
        client.setClientSecret("{noop}secret");
        client.setClientSecretExpiresAt(Instant.ofEpochSecond(10000L));
        client.setClientName("messaging-client");
        client.setClientAuthenticationMethods(ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue());
        client.setAuthorizationGrantTypes("authorization_code,refresh_token,client_credentials");
        client.setRedirectUris("http://yang1.com:8080/login/oauth2/code/messaging-client-oidc,http://yang1.com:8080/authorized");
        client.setScopes("openid, message.read,message.write");
        client.setClientSettings("");
        client.setTokenSettings("");
        clientRepository.save(client);

         client = new Client();
         client.setId(2l);
        client.setClientId("messaging-client2");
        client.setClientIdIssuedAt(Instant.ofEpochSecond(10000L));
        client.setClientSecret("{noop}secret2");
        client.setClientSecretExpiresAt(Instant.ofEpochSecond(10000L));
        client.setClientName("messaging-client2");
        client.setClientAuthenticationMethods(ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue());
        client.setAuthorizationGrantTypes("authorization_code,refresh_token,client_credentials");
        client.setRedirectUris("http://yang1.com:8081/login/oauth2/code/messaging-client-oidc2,http://yang1.com:8081/authorized");
        client.setScopes("openid, message.read,message.write");
        client.setClientSettings("");
        client.setTokenSettings("");
        clientRepository.save(client);

    }

}
