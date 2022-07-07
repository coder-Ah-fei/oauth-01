package com.example.oauthserver.config;

import java.util.*;

import cn.hutool.json.JSONUtil;
import com.example.oauthserver.entity.Client;
import com.example.oauthserver.repository.ClientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author yang
 */
@Component
public class JpaRegisteredClientRepository implements RegisteredClientRepository {
	private final ClientRepository clientRepository;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public JpaRegisteredClientRepository(ClientRepository clientRepository) {
		Assert.notNull(clientRepository, "clientRepository cannot be null");
		this.clientRepository = clientRepository;

		ClassLoader classLoader = JpaRegisteredClientRepository.class.getClassLoader();
		List<Module> securityModules = SecurityJackson2Modules.getModules(classLoader);
		this.objectMapper.registerModules(securityModules);
		this.objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
	}

	@Override
	public void save(RegisteredClient registeredClient) {
		Assert.notNull(registeredClient, "registeredClient cannot be null");
		this.clientRepository.save(toEntity(registeredClient));
	}

	@Override
	public RegisteredClient findById(String id) {
		Assert.hasText(id, "id cannot be empty");
		return this.clientRepository.findById(Long.valueOf(id)).map(this::toObject).orElse(null);
	}

	@Override
	public RegisteredClient findByClientId(String clientId) {
		Assert.hasText(clientId, "clientId cannot be empty");
		System.out.println("clientId: " + clientId);
		return this.clientRepository.findByClientId(clientId).map(this::toObject).orElse(null);
	}

	private RegisteredClient toObject(Client client) {
		Set<String> clientAuthenticationMethods = StringUtils.commaDelimitedListToSet(
				client.getClientAuthenticationMethods());
		Set<String> authorizationGrantTypes = StringUtils.commaDelimitedListToSet(
				client.getAuthorizationGrantTypes());
		Set<String> redirectUris = StringUtils.commaDelimitedListToSet(
				client.getRedirectUris());
		Set<String> clientScopes = StringUtils.commaDelimitedListToSet(
				client.getScopes());

		RegisteredClient.Builder builder = RegisteredClient.withId(String.valueOf(client.getId()))
				.clientId(client.getClientId())
				.clientIdIssuedAt(client.getClientIdIssuedAt())
				.clientSecret(client.getClientSecret())
				.clientSecretExpiresAt(client.getClientSecretExpiresAt())
				.clientName(client.getClientName())
				.clientAuthenticationMethods(authenticationMethods ->
						clientAuthenticationMethods.forEach(authenticationMethod ->
								authenticationMethods.add(resolveClientAuthenticationMethod(authenticationMethod))))
				.authorizationGrantTypes((grantTypes) ->
						authorizationGrantTypes.forEach(grantType ->
								grantTypes.add(resolveAuthorizationGrantType(grantType))))
				.redirectUris((uris) -> uris.addAll(redirectUris))
				.scopes((scopes) -> scopes.addAll(clientScopes));

		Map<String, Object> clientSettingsMap = parseMap(client.getClientSettings());
		builder.clientSettings(ClientSettings.withSettings(clientSettingsMap).build());

		Map<String, Object> tokenSettingsMap = parseMap(client.getTokenSettings());
		tokenSettingsMap.put("settings.token.access-token-format", OAuth2TokenFormat.SELF_CONTAINED);
		builder.tokenSettings(TokenSettings.withSettings(tokenSettingsMap).build());

		return builder.build();
	}

	private Client toEntity(RegisteredClient registeredClient) {
		List<String> clientAuthenticationMethods = new ArrayList<>(registeredClient.getClientAuthenticationMethods().size());
		registeredClient.getClientAuthenticationMethods().forEach(clientAuthenticationMethod ->
				clientAuthenticationMethods.add(clientAuthenticationMethod.getValue()));

		List<String> authorizationGrantTypes = new ArrayList<>(registeredClient.getAuthorizationGrantTypes().size());
		registeredClient.getAuthorizationGrantTypes().forEach(authorizationGrantType ->
				authorizationGrantTypes.add(authorizationGrantType.getValue()));

		Client entity = new Client();
		entity.setId(Long.valueOf(registeredClient.getId()));
		entity.setClientId(registeredClient.getClientId());
		entity.setClientIdIssuedAt(registeredClient.getClientIdIssuedAt());
		entity.setClientSecret(registeredClient.getClientSecret());
		entity.setClientSecretExpiresAt(registeredClient.getClientSecretExpiresAt());
		entity.setClientName(registeredClient.getClientName());
		entity.setClientAuthenticationMethods(StringUtils.collectionToCommaDelimitedString(clientAuthenticationMethods));
		entity.setAuthorizationGrantTypes(StringUtils.collectionToCommaDelimitedString(authorizationGrantTypes));
		entity.setRedirectUris(StringUtils.collectionToCommaDelimitedString(registeredClient.getRedirectUris()));
		entity.setScopes(StringUtils.collectionToCommaDelimitedString(registeredClient.getScopes()));
		entity.setClientSettings(writeMap(registeredClient.getClientSettings().getSettings()));
		entity.setTokenSettings(writeMap(registeredClient.getTokenSettings().getSettings()));

		return entity;
	}

	private Map<String, Object> parseMap(String data) {

		try {
			return objectMapper.readValue(data, new TypeReference<Map<String, Object>>() {});
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex.getMessage(), ex);
		}
	}

	private String writeMap(Map<String, Object> data) {
		try {
			return this.objectMapper.writeValueAsString(data);
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex.getMessage(), ex);
		}
	}

	private static AuthorizationGrantType resolveAuthorizationGrantType(String authorizationGrantType) {
		if (AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equals(authorizationGrantType)) {
			return AuthorizationGrantType.AUTHORIZATION_CODE;
		} else if (AuthorizationGrantType.CLIENT_CREDENTIALS.getValue().equals(authorizationGrantType)) {
			return AuthorizationGrantType.CLIENT_CREDENTIALS;
		} else if (AuthorizationGrantType.REFRESH_TOKEN.getValue().equals(authorizationGrantType)) {
			return AuthorizationGrantType.REFRESH_TOKEN;
		}
		return new AuthorizationGrantType(authorizationGrantType);              // Custom authorization grant type
	}

	private static ClientAuthenticationMethod resolveClientAuthenticationMethod(String clientAuthenticationMethod) {
		if (ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue().equals(clientAuthenticationMethod)) {
			return ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
		} else if (ClientAuthenticationMethod.CLIENT_SECRET_POST.getValue().equals(clientAuthenticationMethod)) {
			return ClientAuthenticationMethod.CLIENT_SECRET_POST;
		} else if (ClientAuthenticationMethod.NONE.getValue().equals(clientAuthenticationMethod)) {
			return ClientAuthenticationMethod.NONE;
		}
		return new ClientAuthenticationMethod(clientAuthenticationMethod);      // Custom client authentication method
	}

}

