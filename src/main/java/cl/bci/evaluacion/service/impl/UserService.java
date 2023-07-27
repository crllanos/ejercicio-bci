package cl.bci.evaluacion.service.impl;

import cl.bci.evaluacion.entity.UserEntity;
import cl.bci.evaluacion.repository.UserRepository;
import cl.bci.evaluacion.service.IUserService;
import cl.bci.evaluacion.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final Util util;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)$");

    @Override
    public UserEntity saveUser(UserEntity user) {
        log.info(String.format("UserRegistryService.saveUser() : %s", util.obj2Json(user)));

        // Validates password
        if(StringUtils.isEmpty(user.getPassword())  || !validatePassword(user.getPassword())){
            throw new IllegalArgumentException("Invalid password");
        }

        // validates email
        if(StringUtils.isEmpty(user.getEmail()) || !validateEmail(user.getEmail())){
            throw new IllegalArgumentException("Invalid e-mail");
        }

        // validar email unique
        if(!ObjectUtils.isEmpty(userRepository.findByEmail(user.getEmail()))){
            throw new IllegalArgumentException("El correo ya esta registrado");
        }

        user.setId(UUID.randomUUID());
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setToken(user.getId().toString()); // @todo Cambiar por jwt
        // para efectos del ejemplo, el usuario quedará activo de manera aleatoria
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10);
        user.setActive(randomNum %2 == 0);

        log.info(String.format("Registro a persistir: %s", util.obj2Json(user)));
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public UserEntity findById(String id) {
        return userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException(String.format("User %s not found", id)));
    }


    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private static boolean validatePassword(String passw) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passw);
        return !matcher.find();
    }

}
