package com.Sigma.SigmaBackEnd.controllers;

import com.Sigma.SigmaBackEnd.dtos.UserModelDTO;
import com.Sigma.SigmaBackEnd.model.UserModel;
import com.Sigma.SigmaBackEnd.services.TokenService;
import com.Sigma.SigmaBackEnd.services.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserModelController {

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<UserModelDTO> findByEmail(@RequestHeader("Authorization") String token){
        var authorizationHeader = token;
        authorizationHeader = authorizationHeader.replace("Bearer ", "");
        String subject = tokenService.getSubject(authorizationHeader);

        UserModel user = userModelService.findByEmailDefault(subject);
        UserModelDTO userModelDTO = new UserModelDTO(user);
        return ResponseEntity.ok().body(userModelDTO);
    }
}
