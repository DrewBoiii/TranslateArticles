package translatearticles.persistence.registration;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import translatearticles.persistence.model.User;

@Data
public class RegistrationForm {

    private final String username;
    private final String password;
    private final String email;
    private final String phoneNumber;

    public User toUser(PasswordEncoder encoder){
        return new User(username, encoder.encode(password), email, phoneNumber);
    }

}
