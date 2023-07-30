package cl.bci.evaluacion.service.impl;

import cl.bci.evaluacion.entity.RoleEntity;
import cl.bci.evaluacion.repository.RoleRepository;
import cl.bci.evaluacion.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity saveRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    @Override
    public RoleEntity findByRolename(String rolename) {
        return roleRepository.findByRolename(rolename);
    }
}
