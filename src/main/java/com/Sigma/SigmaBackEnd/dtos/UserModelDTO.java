package com.Sigma.SigmaBackEnd.dtos;

import com.Sigma.SigmaBackEnd.Enums.Role;
import com.Sigma.SigmaBackEnd.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserModelDTO {

    private String email;
    private Role role;

    public UserModelDTO (UserModel userModel){
        this.email = userModel.getEmail();
        this.role = userModel.getRole();
    }
}
