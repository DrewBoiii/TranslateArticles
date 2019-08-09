package translatearticles.services.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import translatearticles.persistence.dao.UserRepository;
import translatearticles.persistence.model.User;
import translatearticles.services.dao.UserDetailsServiceRepository;

@Slf4j
@Service
public class UserDetailsService implements UserDetailsServiceRepository {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user != null){
            log.info("User was found:" + user.toString());
            return user;
        }
        throw new UsernameNotFoundException("user " + username + " not found");
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
        log.info("Saved user:" + user.toString());
    }
}
