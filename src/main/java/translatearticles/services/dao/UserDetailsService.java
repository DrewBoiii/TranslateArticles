package translatearticles.services.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import translatearticles.exception.EmailNotFoundException;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    UserDetails loadUserByEmail(String email) throws EmailNotFoundException;

}
