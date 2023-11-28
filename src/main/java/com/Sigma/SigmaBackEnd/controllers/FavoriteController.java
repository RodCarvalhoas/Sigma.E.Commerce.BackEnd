package com.Sigma.SigmaBackEnd.controllers;

import com.Sigma.SigmaBackEnd.model.Favorite;
import com.Sigma.SigmaBackEnd.model.Product;
import com.Sigma.SigmaBackEnd.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/favorite")
@CrossOrigin(origins = "http://localhost:3000")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<List<Favorite>> findAll(@RequestHeader("Authorization") String token){
        var authorizationHeader = token;

        authorizationHeader = authorizationHeader.replace("Bearer ", "");

        List<Favorite> allFavorites = favoriteService.findAll(authorizationHeader);
        return ResponseEntity.ok().body(allFavorites);
    }

    @PostMapping
    public ResponseEntity<Favorite> create(@RequestHeader("Authorization") String token, @RequestBody Product product){
        var authorizationHeader = token;

        authorizationHeader = authorizationHeader.replace("Bearer ", "");

        favoriteService.create(authorizationHeader, product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String token, @PathVariable UUID productId){
        var authorizationHeader = token;
        authorizationHeader = authorizationHeader.replace("Bearer ", "");

        favoriteService.deleteById(productId, authorizationHeader);
        return ResponseEntity.noContent().build();
    }
}
