package com.ntt.api;

import com.ntt.api.security.JwtProvider;
import com.ntt.api.security.JwtRequest;
import com.ntt.api.security.JwtResponse;
import com.ntt.entity.MyUserDetails;
import com.ntt.service.MyUserDetailsService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BaseApi {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired MyUserDetailsService myUserDetailsService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> checkLogin(@RequestBody JwtRequest jwtRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtRequest.getUsername(),
                        jwtRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwt((MyUserDetails) authentication.getPrincipal());
        JwtResponse jwtResponse = new JwtResponse(jwt);
        return new ResponseEntity<>(jwtResponse, HttpStatus.ACCEPTED);
    }
}
