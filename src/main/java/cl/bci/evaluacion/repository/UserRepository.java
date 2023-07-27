package cl.bci.evaluacion.repository;

import cl.bci.evaluacion.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

    UserEntity findByEmail(String email);
}
