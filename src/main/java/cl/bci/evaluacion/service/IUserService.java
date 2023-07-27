package cl.bci.evaluacion.service;

import cl.bci.evaluacion.entity.UserEntity;

public interface IUserService {
    UserEntity saveUser(UserEntity user);
    //UserBO findUserById(String id);
    //List<UserBO> findAllUsers();
}
