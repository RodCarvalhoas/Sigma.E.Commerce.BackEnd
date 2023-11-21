package com.Sigma.SigmaBackEnd.controllers;

import com.Sigma.SigmaBackEnd.dtos.DataTokenJWT;
import com.Sigma.SigmaBackEnd.dtos.LoginDTO;
import com.Sigma.SigmaBackEnd.model.UserModel;
import com.Sigma.SigmaBackEnd.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity postLogin(@RequestBody LoginDTO loginDTO){
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((UserModel) authentication.getPrincipal());

        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }


}
