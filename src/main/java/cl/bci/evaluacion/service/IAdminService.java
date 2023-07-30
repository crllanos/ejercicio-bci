package cl.bci.evaluacion.service;

import cl.bci.evaluacion.entity.AdminEntity;
import org.springframework.stereotype.Service;

@Service
public interface IAdminService {

    AdminEntity saveAdmin(AdminEntity admin);

    AdminEntity findAdmin(String username);

    void addRoleToAdmin(String username, String rolename);
}
