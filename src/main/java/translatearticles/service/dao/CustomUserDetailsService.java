package translatearticles.service.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import translatearticles.exception.EmailNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    UserDetails loadUserByEmail(String email) throws EmailNotFoundException;

}
