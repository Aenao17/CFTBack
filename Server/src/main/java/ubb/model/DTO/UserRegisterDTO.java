package ubb.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ubb.model.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterDTO {
    private String username;
    private String password;
    private String email;

    public final static UserRegisterDTO convertToDTO(User user) {
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
