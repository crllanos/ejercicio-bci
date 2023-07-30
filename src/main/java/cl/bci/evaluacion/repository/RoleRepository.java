package cl.bci.evaluacion.repository;

import cl.bci.evaluacion.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    RoleEntity findByRolename(String rolename);

}
