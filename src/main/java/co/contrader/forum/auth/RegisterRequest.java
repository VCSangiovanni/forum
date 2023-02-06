package co.contrader.forum.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    //User
    private String username;
    private String password;

    //Profilo
    private String firstName;
    private String lastName;
    private String eMail;

}
