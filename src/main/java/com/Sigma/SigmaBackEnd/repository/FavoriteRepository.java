package com.Sigma.SigmaBackEnd.repository;

import com.Sigma.SigmaBackEnd.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
}
