package cl.bci.evaluacion.service;

import cl.bci.evaluacion.entity.RoleEntity;
import org.springframework.stereotype.Service;

@Service
public interface IRoleService {

    RoleEntity saveRole(RoleEntity role);

    RoleEntity findByRolename(String rolename);

}
