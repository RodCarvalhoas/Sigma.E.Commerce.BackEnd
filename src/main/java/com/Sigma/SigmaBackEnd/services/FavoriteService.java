package com.Sigma.SigmaBackEnd.services;

import com.Sigma.SigmaBackEnd.model.Favorite;
import com.Sigma.SigmaBackEnd.model.Product;
import com.Sigma.SigmaBackEnd.model.UserModel;
import com.Sigma.SigmaBackEnd.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserModelService userModelService;

    public List<Favorite> findAll(String token){
        String subject = tokenService.getSubject(token);
        UserModel userModel = userModelService.findByEmailDefault(subject);
        return favoriteRepository.findAllByUser(userModel.getId());
    }

    public Favorite create(String token, Product product){
        String subject = tokenService.getSubject(token);
        UserModel userModel = userModelService.findByEmailDefault(subject);
        Favorite favorite = new Favorite(null, product, userModel);
        return favoriteRepository.save(favorite);
    }

    public void deleteById(UUID productId, String token){
        String subject = tokenService.getSubject(token);
        UserModel userModel = userModelService.findByEmailDefault(subject);

        Favorite favorite = favoriteRepository.findFavoriteByUserAndProduct(userModel.getId(), productId);
        favoriteRepository.deleteById(favorite.getId());
    }
}
