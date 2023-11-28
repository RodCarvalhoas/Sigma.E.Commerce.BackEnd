package com.Sigma.SigmaBackEnd.repository;

import com.Sigma.SigmaBackEnd.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {

    @Query("SELECT obj FROM Favorite obj WHERE obj.userModel.id = :userId")
    List<Favorite> findAllByUser(@Param(value = "userId") UUID userId);

    @Query("SELECT obj FROM Favorite obj WHERE obj.userModel.id = :userId AND obj.product.id = :productId")
    Favorite findFavoriteByUserAndProduct(@Param("userId") UUID userId, @Param("productId") UUID productId);
}
