package co.contrader.forum.dto;

import co.contrader.forum.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String eMail;
    private UserDTO user;

}
