package com.Sigma.SigmaBackEnd.repository;

import com.Sigma.SigmaBackEnd.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserModelRepository extends JpaRepository<UserModel, UUID> {

    UserDetails findByEmail(String login);

    @Query("SELECT obj FROM UserModel obj WHERE obj.email = :email")
    UserModel findByEmailDefault (@Param(value = "email") String email);
}
