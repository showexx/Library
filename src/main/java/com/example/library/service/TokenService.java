package com.example.library.service;


import com.example.library.dto.AuthorizationPersonDTO;
import com.example.library.dto.TokenDTO;
import com.example.library.exception.ApplicationException;
import com.example.library.util.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final PersonService personService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public TokenService(PersonService personService, JwtTokenUtils jwtTokenUtils, AuthenticationManager authenticationManager) {
        this.personService = personService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
    }

    public String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else {
            throw new ApplicationException(HttpStatus.UNAUTHORIZED.value(), "В заголовке токена не найден Authorization");
        }
    }

    public TokenDTO createAuthToken(AuthorizationPersonDTO authorizationPersonDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authorizationPersonDTO.getEmail(), authorizationPersonDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ApplicationException(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль");
        }

        UserDetails userDetails = personService.loadUserByUsername(authorizationPersonDTO.getEmail());
        String token = jwtTokenUtils.generateToken(userDetails);

        return new TokenDTO(token);
    }
}
