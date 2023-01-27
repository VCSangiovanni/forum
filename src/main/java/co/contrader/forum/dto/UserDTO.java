package co.contrader.forum.dto;

import co.contrader.forum.model.Profile;
import co.contrader.forum.utils.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String userName;
    private String password;
    private Long userCreation;
    private String resetPasswordCode;
    private String ActivationCode;
    private Profile userProfile;
    private Role userRole;

}
