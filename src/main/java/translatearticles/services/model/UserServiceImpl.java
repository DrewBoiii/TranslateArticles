package translatearticles.services.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import translatearticles.persistence.dao.RoleRepository;
import translatearticles.persistence.dao.UserRepository;
import translatearticles.persistence.model.Role;
import translatearticles.persistence.model.User;
import translatearticles.services.dao.UserService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(User user) {
        log.info("Saved user:" + user.toString());

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        List<Role> roles = new ArrayList<>();
        for (Role role : roleRepository.findAll()) {
            roles.add(role);
        }

        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        log.info(user.toString());
        return user;
    }
}
