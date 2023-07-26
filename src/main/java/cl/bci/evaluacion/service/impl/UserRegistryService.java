package cl.bci.evaluacion.service.impl;

import cl.bci.evaluacion.entity.UserEntity;
import cl.bci.evaluacion.service.IUserRegistryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class UserRegistryService implements IUserRegistryService {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)$");


    @Override
    public String saveUser(UserEntity user) {
        log.info(String.format("UserRegistryService.saveUser() : %s", user));

        // validates email
        if(StringUtils.isEmpty(user.getEmail()) || !validateEmail(user.getEmail())){
            throw new IllegalArgumentException("Invalid e-mail");
        }
        return null;
    }



    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private static boolean validatePassword(String pwd) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(pwd);
        return matcher.find();
    }

}
