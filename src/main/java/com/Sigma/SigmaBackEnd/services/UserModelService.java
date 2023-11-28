package com.Sigma.SigmaBackEnd.services;

import com.Sigma.SigmaBackEnd.model.UserModel;
import com.Sigma.SigmaBackEnd.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserModelService {

    @Autowired
    private UserModelRepository userModelRepository;

    public UserDetails findByEmail(String email){
        return userModelRepository.findByEmail(email);
    }

    public UserModel findByEmailDefault(String email){
        return userModelRepository.findByEmailDefault(email);
    }


}
