package co.contrader.forum.auth.passwordManager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordDTO {

    private String newPassword;
    private String resetPasswordCode;

}
