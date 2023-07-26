package cl.bci.evaluacion.service.impl;

import cl.bci.evaluacion.entity.UserEntity;
import cl.bci.evaluacion.repository.UserRegistryRepository;
import cl.bci.evaluacion.service.IUserRegistryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRegistryService implements IUserRegistryService {

    private final UserRegistryRepository userRegistryRepository;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)$");


    @Override
    public UserEntity saveUser(UserEntity user) {
        log.info(String.format("UserRegistryService.saveUser() : %s", user));

        // Validates password
        if(StringUtils.isEmpty(user.getPassword())  || !validatePassword(user.getPassword())){
            throw new IllegalArgumentException("Invalid password");
        }

        // validates email
        if(StringUtils.isEmpty(user.getEmail()) || !validateEmail(user.getEmail())){
            throw new IllegalArgumentException("Invalid e-mail");
        }

        // validar email unique
        if(!ObjectUtils.isEmpty(userRegistryRepository.findByEmail(user.getEmail()))){
            throw new IllegalArgumentException("El correo ya esta registrado");
        }


        String uuid = UUID.randomUUID().toString();
        user.setId(uuid);
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setToken(user.getId()); // @todo Cambiar por jwt
        // para efectos del ejemplo, el usuario quedará activo de manera aleatoria
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10);
        user.setActive(randomNum %2 == 0);

        log.info(String.format("registro a persistir: %s", user));

        return userRegistryRepository.save(user);
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
