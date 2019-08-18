package translatearticles.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import translatearticles.persistence.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
