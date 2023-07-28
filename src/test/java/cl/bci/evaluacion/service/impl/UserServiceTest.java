package cl.bci.evaluacion.service.impl;

import cl.bci.evaluacion.entity.UserEntity;
import cl.bci.evaluacion.repository.UserRepository;
import cl.bci.evaluacion.util.Util;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Util util;

    @Test
    public void shouldNotSaveUser_invalidPassword(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(mockUser_invalidPassword("aeiou"));
        });
        String expectedMessage = "Invalid password";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldNotSaveUser_invalidEmail(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(mockUser_invalidEmail("correo_novalido.cl"));
        });
        String expectedMessage = "Invalid e-mail";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldNotSaveUser_uniqueEmail(){
        String mailRegistrado = "email@registrado.cl";
        when(userRepository.findByEmail(mailRegistrado)).thenReturn(UserEntity.builder().email(mailRegistrado).build());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(mockUser_uniqueEmail(mailRegistrado));
        });
        String expectedMessage = "Email already exists";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    public void shouldSaveUser_Ok(){
        when(userRepository.save(any())).thenReturn(mockUser());
        when(util.obj2Json(any())).thenReturn("obj2Json");

        UserEntity a = userService.saveUser(mockUser());
        assertNotNull(a);
    }



    /**
     * mock data
     */
    private UserEntity mockUser(){
        return UserEntity.builder().name("test").email("correo@valido.cl").password("AbCdEfG1").build();
    }

    private UserEntity mockUser_invalidPassword(String pass){
        UserEntity user = mockUser();
        user.setPassword(pass);
        return user;
    }

    private UserEntity mockUser_invalidEmail(String mail){
        UserEntity user = mockUser();
        user.setEmail(mail);
        return user;
    }

    private UserEntity mockUser_uniqueEmail(String unique){
        UserEntity user = mockUser();
        user.setEmail(unique);
        return user;
    }

}