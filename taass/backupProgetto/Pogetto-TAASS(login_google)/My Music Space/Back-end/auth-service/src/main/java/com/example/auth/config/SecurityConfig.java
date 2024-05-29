package com.example.auth.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

// in springboot 3, le prime 2 annotazioni devono sempre andare insieme
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    // all'avvio dell'applicazione, spring security cercherà il @Bean di
    // tipo SecurityFilterChain, il quale è il respondabile della sicurezza di HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // csrf().disable() è deprecata

                // qua creamo una whitelist, non sempre abbiamo bisogno di un token (es. creazione di un account)
                // dentro i matchers ci sono i whitelistati, tutti gli altri (anyRequest()) devono essere autenticati.
                // il metodo and() è deprecato e non ho capito con cosa sia stato sostituito
                .authorizeHttpRequests(auth -> {
                            // voglio autorizzare tutti i metodi che ho dentro il mio authController
                            auth.requestMatchers("/api/auth/**").permitAll();
                            auth.anyRequest().authenticated();
                        }
                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)

                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                // devo chiamare questo filtro prima di quello dello username e password
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
