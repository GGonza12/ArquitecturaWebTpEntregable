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
                        .requestMatchers(HttpMethod.POST, "/api/facuturacion").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.PUT, "/api/facuturacion/*").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.POST, "/api/monopatin").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/monopatin/*").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.PUT, "/api/monopatin/*").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.PUT, "/api/monopatin/mantenimiento/*").hasAuthority(Rol.ROL_MANTENIMIENTO.name())
                        .requestMatchers(HttpMethod.GET, "/api/monopatin/cerca").hasAnyAuthority(Rol.ROL_USUARIO.name(),Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.POST, "/api/parada").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.PUT, "/api/parada/*").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/parada/*").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.GET, "/api/reporte/uso-usuario").hasAuthority(Rol.ROL_USUARIO.name())
                        .requestMatchers(HttpMethod.GET, "/api/reporte/**").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        /*
                                                .requestMatchers(HttpMethod.GET, "/api/reporte/km").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.GET, "/api/reporte/km-pausa").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.GET, "/api/reporte/facturacion").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.GET, "/api/reporte/monopatin-cantidad-viajes").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.GET, "/api/reporte/monopatin-cerca").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.GET, "/api/reporte/usuarios-mas-activos").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                         */

                        .requestMatchers(HttpMethod.GET, "/api/usuario/relacionados/*").hasAuthority(Rol.ROL_USUARIO.name())
                        .requestMatchers(HttpMethod.GET, "/api/usuario/relacionados/").hasAuthority(Rol.ROL_USUARIO.name())
                        .requestMatchers(HttpMethod.PUT, "/api/cuenta/desactivarCuenta/*").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.GET, "/api/viaje/reporte/km").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.GET, "/api/viaje/reporte/km/pausa").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.GET, "/api/viaje/monopatin-cantidad-viajes").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.GET, "/api/viaje/total-facturado").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.GET, "/api/viaje/ranking-usuarios").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
                        .requestMatchers(HttpMethod.GET, "/api/viaje/uso").hasAuthority(Rol.ROL_ADMINISTRADOR.name())
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
/*
Facturacion Controller
-api/facturacion (Post) Admin (agrega precio vigencia por fecha) +
-api/facturacion/{id} (Put) Admin (actualiza precio) +
-api/facturacion/** permitAll() +
Monopatin Controller
-api/monopatin (Post) Admin (agregar monopatin) +
-api/monopatin/{id} (Delete) Admin (eliminar monopatin) +
-api/monopatin/{id} (Put) Mantenimiento,Admin (modificar monopatin) +
-api/monopatin/mantenimiento/{id} (Put) Mantenimiento (estado monopatin a mantenimiento) +
-api/monopatin/cerca (Get) Usuario (obtener una lista de monopatines cerca) +
-api/monopatin/** permitAll() +

Parada Controller
-api/parada (Post) Admin (agregar parada) +
-api/parada/{id} (Put) Admin (modificar parada) +
-api/parada/{id} (Delete) Admin (eliminar parada)+
-api/parada/** permitAll()+
Reporte Controller
-api/reporte/km (Get) Admin (4.A) +
-api/reporte/km-pausa (Get) Admin (4.A) +
-api/reporte/facturacion (Get) Admin (4.D) +
-api/reporte/monopatin-cantidad-viajes (Get) Admin (4.C) +
-api/reporte/monopatin-cerca (Get) Admin (4.G) +
-api/reporte/usuarios-mas-activos (Get) Admin (4.E) +
-api/reporte/uso-usuario (Get) Usuario (4.H) +
Usuario Controller
-api/usuario/relacionados/{idUsuario} (Get) Usuario (4.H) +
-api/usuario/** ADMIN, USUARIO +
Cuenta Controller
-api/cuenta/desactivarCuenta/{id} (Put) Admin (4.B) +
-api/cuenta/** permitAll() +
Viaje Controller
-api/viaje/reporte/km (Get) Admin (4.A) +
-api/viaje/reporte/km/pausa (Get) Admin (4.A) +
-api/viaje/monopatin-cantidad-viajes (Get) Admin (4.C) +
-api/viaje/total-facturado (Get) Admin (4.D) +
-api/viaje/ranking-usuarios (Get) Admin (4.E) +
-api/viaje/uso (Get) Admin (4.H) +
-api/viaje/** permitAll() +

 */




