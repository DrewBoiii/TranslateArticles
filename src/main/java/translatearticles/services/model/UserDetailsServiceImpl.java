package translatearticles.services.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import translatearticles.exception.EmailNotFoundException;
import translatearticles.persistence.dao.UserRepository;
import translatearticles.persistence.model.Role;
import translatearticles.persistence.model.User;
import translatearticles.services.dao.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("user " + username + " not found");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role: user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    // TODO: 17.08.2019 make this method like method above
    @Override
    public UserDetails loadUserByEmail(String email) throws EmailNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if(user != null){
            log.info("User was found:" + user.toString());
            return user;
        }
        throw new EmailNotFoundException("user with current email " + email + " was not found");
    }

}
