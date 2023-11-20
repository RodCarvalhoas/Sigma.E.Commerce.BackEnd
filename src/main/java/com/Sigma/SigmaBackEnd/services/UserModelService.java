package com.Sigma.SigmaBackEnd.services;

import com.Sigma.SigmaBackEnd.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserModelService {

    @Autowired
    private UserModelRepository userModelRepository;


}
