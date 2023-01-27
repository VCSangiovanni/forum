package co.contrader.forum.dto;

import co.contrader.forum.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String eMail;
    private User user;

}
