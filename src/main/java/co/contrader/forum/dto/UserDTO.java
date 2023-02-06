package co.contrader.forum.dto;

import co.contrader.forum.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private Long userCreation;
    private String resetPasswordCode;
    private String ActivationCode;
    private boolean isActive;
    private Role userRole;

}
