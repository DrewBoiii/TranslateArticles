package translatearticles.service.dao;

import translatearticles.persistence.model.User;
import translatearticles.web.dto.UserRegistrationDto;

public interface UserService {

    User save(UserRegistrationDto registration);
    User findByUsername(String username);
    User findByEmail(String email);

}
