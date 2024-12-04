package com.example.auth.authentication;

import com.example.auth.config.JwtService;
import com.example.auth.user.Role;
import com.example.auth.user.User;
import com.example.auth.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;    // ci deve essere ogni volta che devo fare operazioni sul db
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public boolean existEmail(String email){
        return repository.existsByEmail(email);
    }
    //public boolean checkPassword(String password){return repository.checkPassword(password);}

    // crea lo user, lo salva nel db e ritorna il token generato
    public AuthenticationResponse register(RegisterRequest request){
        /*
        System.out.println("\n");
        System.out.println(request.getFirstName());
        System.out.println(request.getLastName());
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());
        System.out.println("\n");
        */

        // TODO: potrebbe essere fatto meglio?
        // controllo che l'utente non esista
        if(repository.findByEmail(request.getEmail()).isPresent()){
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists!");
            return AuthenticationResponse.builder()
                    .token("")
                    .response("Error: username already exist")
                    .build();
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        List<String> res = List.of(user.getEmail(), user.getFirstName(), user.getLastName());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .response(res.toString())
                .build();
    }

    // fa tutto l'authenticationManager
    // se l'email è presente nel db controllo la password, altrimenti restituisco un messaggio di errore
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        if(existEmail(request.getEmail())){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )

            );

            // se arrivo a questo punto, l'utente si è autenticato
            // devo generare il token e restituirlo

            var user = repository.findByEmail(request.getEmail()).orElseThrow();
            List<String> res = List.of(user.getEmail(), user.getFirstName(), user.getLastName());
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .response(res.toString())
                    .build();

            //TODO: gestire la password errata

        } else {
            return AuthenticationResponse.builder()
                    .token("")
                    .response("User not found.")
                    .build();
        }
    }

}
