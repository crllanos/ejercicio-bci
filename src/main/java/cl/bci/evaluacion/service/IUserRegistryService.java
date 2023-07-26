package cl.bci.evaluacion.service;

import cl.bci.evaluacion.entity.UserEntity;

public interface IUserRegistryService {
    String saveUser(UserEntity user);
    //UserBO findUserById(String id);
    //List<UserBO> findAllUsers();
}
