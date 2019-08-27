package translatearticles.service.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import translatearticles.persistence.dao.UserRepository;
import translatearticles.persistence.model.Role;
import translatearticles.persistence.model.User;
import translatearticles.service.dao.UserService;
import translatearticles.web.dto.UserRegistrationDto;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User save(UserRegistrationDto registration) {
        User user = getInitUser(registration);

        log.info("Saved user:" + user.toString());

        return userRepository.save(user);
    }

    private User getInitUser(UserRegistrationDto registration){
        User user = new User();

        user.setUsername(registration.getUsername());
        user.setPhoneNumber(registration.getPhoneNumber());
        user.setEmail(registration.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));

        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
//        log.info(user.toString());
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
//        log.info(user.toString());
        return user;
    }
}
