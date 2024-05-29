package com.example.auth.config;

import com.example.auth.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.AuthProvider;

// andranno qui tutti i metodi di configurazione (Bean eccetera)
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository repository;

    // devo rivedere le lambda ex
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> repository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }


    // è il data access object (DAO) il quale è responsabile di
    // fare fetch di user details e fare encoding delle password
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // devo dire quale service usare per fare fetch delle info dello
        // user perchè potrei avere multiple implementazioni di UserDetail
        authProvider.setUserDetailsService(userDetailsService());

        // quale password encoder vogliamo usare nella nostra application
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
