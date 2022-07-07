package com.example.oauthserver.entity;

import java.time.Instant;

import javax.persistence.*;

@Entity
@Table(name = "`authorization`")
public class Authorization {
	@Id
	@Column
	private String id;
	private String registeredClientId;
	private String principalName;
	private String authorizationGrantType;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String attributes;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String state;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String authorizationCodeValue;
	private Instant authorizationCodeIssuedAt;
	private Instant authorizationCodeExpiresAt;
	private String authorizationCodeMetadata;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String accessTokenValue;
	private Instant accessTokenIssuedAt;
	private Instant accessTokenExpiresAt;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String accessTokenMetadata;
	private String accessTokenType;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String accessTokenScopes;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String refreshTokenValue;
	private Instant refreshTokenIssuedAt;
	private Instant refreshTokenExpiresAt;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String refreshTokenMetadata;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String oidcIdTokenValue;
	private Instant oidcIdTokenIssuedAt;
	private Instant oidcIdTokenExpiresAt;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String oidcIdTokenMetadata;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String oidcIdTokenClaims;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegisteredClientId() {
		return registeredClientId;
	}

	public void setRegisteredClientId(String registeredClientId) {
		this.registeredClientId = registeredClientId;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getAuthorizationGrantType() {
		return authorizationGrantType;
	}

	public void setAuthorizationGrantType(String authorizationGrantType) {
		this.authorizationGrantType = authorizationGrantType;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAuthorizationCodeValue() {
		return authorizationCodeValue;
	}

	public void setAuthorizationCodeValue(String authorizationCode) {
		this.authorizationCodeValue = authorizationCode;
	}

	public Instant getAuthorizationCodeIssuedAt() {
		return authorizationCodeIssuedAt;
	}

	public void setAuthorizationCodeIssuedAt(Instant authorizationCodeIssuedAt) {
		this.authorizationCodeIssuedAt = authorizationCodeIssuedAt;
	}

	public Instant getAuthorizationCodeExpiresAt() {
		return authorizationCodeExpiresAt;
	}

	public void setAuthorizationCodeExpiresAt(Instant authorizationCodeExpiresAt) {
		this.authorizationCodeExpiresAt = authorizationCodeExpiresAt;
	}

	public String getAuthorizationCodeMetadata() {
		return authorizationCodeMetadata;
	}

	public void setAuthorizationCodeMetadata(String authorizationCodeMetadata) {
		this.authorizationCodeMetadata = authorizationCodeMetadata;
	}

	public String getAccessTokenValue() {
		return accessTokenValue;
	}

	public void setAccessTokenValue(String accessToken) {
		this.accessTokenValue = accessToken;
	}

	public Instant getAccessTokenIssuedAt() {
		return accessTokenIssuedAt;
	}

	public void setAccessTokenIssuedAt(Instant accessTokenIssuedAt) {
		this.accessTokenIssuedAt = accessTokenIssuedAt;
	}

	public Instant getAccessTokenExpiresAt() {
		return accessTokenExpiresAt;
	}

	public void setAccessTokenExpiresAt(Instant accessTokenExpiresAt) {
		this.accessTokenExpiresAt = accessTokenExpiresAt;
	}

	public String getAccessTokenMetadata() {
		return accessTokenMetadata;
	}

	public void setAccessTokenMetadata(String accessTokenMetadata) {
		this.accessTokenMetadata = accessTokenMetadata;
	}

	public String getAccessTokenType() {
		return accessTokenType;
	}

	public void setAccessTokenType(String accessTokenType) {
		this.accessTokenType = accessTokenType;
	}

	public String getAccessTokenScopes() {
		return accessTokenScopes;
	}

	public void setAccessTokenScopes(String accessTokenScopes) {
		this.accessTokenScopes = accessTokenScopes;
	}

	public String getRefreshTokenValue() {
		return refreshTokenValue;
	}

	public void setRefreshTokenValue(String refreshToken) {
		this.refreshTokenValue = refreshToken;
	}

	public Instant getRefreshTokenIssuedAt() {
		return refreshTokenIssuedAt;
	}

	public void setRefreshTokenIssuedAt(Instant refreshTokenIssuedAt) {
		this.refreshTokenIssuedAt = refreshTokenIssuedAt;
	}

	public Instant getRefreshTokenExpiresAt() {
		return refreshTokenExpiresAt;
	}

	public void setRefreshTokenExpiresAt(Instant refreshTokenExpiresAt) {
		this.refreshTokenExpiresAt = refreshTokenExpiresAt;
	}

	public String getRefreshTokenMetadata() {
		return refreshTokenMetadata;
	}

	public void setRefreshTokenMetadata(String refreshTokenMetadata) {
		this.refreshTokenMetadata = refreshTokenMetadata;
	}

	public String getOidcIdTokenValue() {
		return oidcIdTokenValue;
	}

	public void setOidcIdTokenValue(String idToken) {
		this.oidcIdTokenValue = idToken;
	}

	public Instant getOidcIdTokenIssuedAt() {
		return oidcIdTokenIssuedAt;
	}

	public void setOidcIdTokenIssuedAt(Instant idTokenIssuedAt) {
		this.oidcIdTokenIssuedAt = idTokenIssuedAt;
	}

	public Instant getOidcIdTokenExpiresAt() {
		return oidcIdTokenExpiresAt;
	}

	public void setOidcIdTokenExpiresAt(Instant idTokenExpiresAt) {
		this.oidcIdTokenExpiresAt = idTokenExpiresAt;
	}

	public String getOidcIdTokenMetadata() {
		return oidcIdTokenMetadata;
	}

	public void setOidcIdTokenMetadata(String idTokenMetadata) {
		this.oidcIdTokenMetadata = idTokenMetadata;
	}

	public String getOidcIdTokenClaims() {
		return oidcIdTokenClaims;
	}

	public void setOidcIdTokenClaims(String idTokenClaims) {
		this.oidcIdTokenClaims = idTokenClaims;
	}

}

