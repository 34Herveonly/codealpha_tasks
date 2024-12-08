package net.Management001.Student_mgt_system_SpringBoot.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    // Getters and Setters
    private String email;
    private String password;

}
