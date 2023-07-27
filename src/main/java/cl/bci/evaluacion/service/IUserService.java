package cl.bci.evaluacion.service;

import cl.bci.evaluacion.entity.UserEntity;

import java.util.List;

public interface IUserService {

    UserEntity saveUser(UserEntity user);

    List<UserEntity> findAll();

    UserEntity findById(String id);

    UserEntity update(String id, UserEntity old);
}
