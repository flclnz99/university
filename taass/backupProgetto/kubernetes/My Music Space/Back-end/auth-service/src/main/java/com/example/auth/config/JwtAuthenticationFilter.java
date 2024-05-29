package com.example.auth.config;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor    // crea un costruttore che usa ogni attributo final
public class JwtAuthenticationFilter  extends OncePerRequestFilter{
// OncePerRequestFilter serve perchè il filtro si deve attivare per ogni richiesta


    // mi serve per estrarre dal token, l'username con cui poi cercare nel db
    private final JwtService jwtService;

    // serve per le operazioni sul db, ha già un metodo (è una interface) ma serve fare override
    private final UserDetailsService userDetailsService;


    // request è la nostra richiesta
    // response è la nostra risposta alla richiesta
    // filterChain contiene la lista dei filtri da eseguire
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {

        // quando si fa una chiamata, dobbiamo passare il token JWT nell'header,
        // questa variabile serve per estrarre l'header dalla richiesta
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // controllo che l'header non sia vuoto e che cominci con
        // 'Bearer', è lo standard
        if(authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        // estraggo il token, comincio dalla posizione 7 perchè è
        // la posizione in cui finisce 'Bearer'
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        // devo controllare se lo user è valido e se non è già autenticato, perchè
        // se lo è già non ho bisogno di fare tutti i controlli precedenti
        if((userEmail != null) && (SecurityContextHolder.getContext().getAuthentication() == null)){

            // a questo punto devo estrarre lo user dal db
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if(jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );      // serve al security context holder per aggiornare il security context

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // finalmente, aggiorno il security context holder
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // dobbiamo passare al prossimo filtro
        filterChain.doFilter(request,response);
    }
}
