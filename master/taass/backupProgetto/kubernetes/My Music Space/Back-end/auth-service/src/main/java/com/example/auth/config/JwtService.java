package com.example.auth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "uDO7OgREWuo4jDTsaalBPyAgPt9oL3/joWhXrenoPcuxCWEHAqg3f4xwmTNd7Zhd";

    public String extractUsername(String token){
        //la subject dovrebbe essere lo username/email
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimRisolver){
        final Claims claims = extractAllClaims(token);
        return claimRisolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){   //per generare token senza passare la mappa con i claims
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))                   // quando il claim è stato creato, serve per
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))      // capire se è scaduto e la data di scadenza
                .signWith(getSingInKey(), SignatureAlgorithm.HS256)
                .compact();     // questo metodo genera e ritorna il token
    }

    // vogliamo controllare se l'username in input è lo stesso che c'è nel token e se è scaduto o no
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !iseNotExpired(token);
    }

    private boolean iseNotExpired(String token) {
        return extracExpiration(token).before(new Date());
    }

    private Date extracExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // la signInKey è un segreto che è usato per firmare digitalmente il JWT token.
    // è usata per creare la signature part del JWT, la quale è usata per verificare
    // che il sender del JWT sia veramente chi dice di essere e che il messaggio
    // non è stato modificato. la signInKey è usata in combo con il signInAlgorithm
    // specificato nel header (vai a vedere come è formato un JWT) per creare la firma
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSingInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
