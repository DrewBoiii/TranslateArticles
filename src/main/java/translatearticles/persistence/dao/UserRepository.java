package translatearticles.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import translatearticles.persistence.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String username);
    User findUserByEmail(String email);

}
