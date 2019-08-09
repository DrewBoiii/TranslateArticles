package translatearticles.services.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import translatearticles.persistence.model.User;

public interface UserDetailsServiceRepository {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    void save (User user);

}
