package com.solsist.server.config;


import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.proc.DefaultJOSEObjectTypeVerifier;
import com.nimbusds.jose.proc.SecurityContext;
import com.solsist.server.util.AudienceValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${logto.audience}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwksUri;

    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwksUri)
                // The decoder should support the token type: Access Token + JWT.
                .jwtProcessorCustomizer(customizer -> customizer.setJWSTypeVerifier(
                        new DefaultJOSEObjectTypeVerifier<SecurityContext>(new JOSEObjectType("at+jwt"))))
                .jwsAlgorithm(SignatureAlgorithm.ES384)
                .build();

        jwtDecoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(
                new AudienceValidator(audience),
                new JwtIssuerValidator(issuer),
                new JwtTimestampValidator()));

        return jwtDecoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt).cors().and()
                .authorizeRequests(customizer -> customizer
                        // Only authenticated requests can access your protected APIs
                        .mvcMatchers("/", "/secret").authenticated()
//                        .mvcMatchers().hasAuthority("SCOPE_admin")
                        // Anyone can access the public profile.
                        .mvcMatchers("/profile").permitAll()
                );
        return http.build();
    }
}