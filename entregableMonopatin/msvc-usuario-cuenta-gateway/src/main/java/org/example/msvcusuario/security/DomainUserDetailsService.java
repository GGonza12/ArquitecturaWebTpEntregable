package org.example.msvcusuario.security;

import org.example.msvcusuario.dto.UserDTO;
import org.example.msvcusuario.model.Usuario;
import org.example.msvcusuario.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Component
public class DomainUserDetailsService implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UsuarioRepository userRepository;

    public DomainUserDetailsService( UsuarioRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username ) {
        log.debug("Authenticating {}", username);


        return userRepository
                .findOneWithAuthoritiesByUsernameIgnoreCase( username.toLowerCase() )
                .map( this::createSpringSecurityUser )
                .orElseThrow( () -> new UsernameNotFoundException( "El usuario " + username + " no existe" ) );
    }

    private UserDetails createSpringSecurityUser( Usuario user ) {
       GrantedAuthority grantedAuthoritie = new SimpleGrantedAuthority(user.getRol().name());

        return new org.springframework.security.core.userdetails.User( user.getUserName(), user.getPassword(), Collections.singleton(grantedAuthoritie) );
    }
}
