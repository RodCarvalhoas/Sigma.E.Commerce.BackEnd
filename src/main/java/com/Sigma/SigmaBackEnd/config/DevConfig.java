package com.Sigma.SigmaBackEnd.config;

import com.Sigma.SigmaBackEnd.services.DevBDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DevBDService devBDService;

    @Bean
    public void instanciaBaseDeDados(){
        devBDService.startBDH2();
    }
}