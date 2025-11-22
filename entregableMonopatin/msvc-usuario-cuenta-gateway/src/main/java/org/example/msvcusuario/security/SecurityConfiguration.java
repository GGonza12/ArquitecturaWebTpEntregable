package org.example.msvcusuario.security;

import org.example.msvcusuario.model.Rol;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final TokenProvider tokenProvider;

    public SecurityConfiguration( TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http ) throws Exception {
        http
                .csrf( AbstractHttpConfigurer::disable );
        http
                .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http
                .securityMatcher("/api/**" )
                .authorizeHttpRequests( authz -> authz
                        .requestMatchers(HttpMethod.POST, "/api/authenticate").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuario").permitAll()
                        .requestMatchers("/api/cuenta/**").permitAll()
                        .requestMatchers( "/api/usuario/**").permitAll()
                        .requestMatchers( "/api/monopatin/**").permitAll()
                        .requestMatchers( "/api/viaje/**").permitAll()
                        .requestMatchers( "/api/reporte/**").permitAll()
                        .requestMatchers( "/api/facturacion/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic( Customizer.withDefaults() )
                .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }
}
