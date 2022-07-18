//package com.example.oauthserver.filter;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class UserAuthenticationProvider implements AuthenticationProvider {
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        System.out.println("UserAuthenticationProvider.authenticate");
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("root")
//                .password("root")
//                .roles("USER")
//                .authorities("openid")
//                .build();;
//
//
//        return new UsernamePasswordAuthenticationToken(userDetails, "root", authorities);
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        System.out.println("UserAuthenticationProvider.supports");
//        return true;
//    }
//}