package co.contrader.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String eMail;

}
