package cl.bci.evaluacion.controller;

import cl.bci.evaluacion.dto.PhoneDTO;
import cl.bci.evaluacion.dto.RegistryResponseDTO;
import cl.bci.evaluacion.dto.UserRegistryRequestDTO;
import cl.bci.evaluacion.dto.UserRegistryResponseDTO;
import cl.bci.evaluacion.entity.PhoneEntity;
import cl.bci.evaluacion.entity.UserEntity;
import cl.bci.evaluacion.service.IUserService;
import cl.bci.evaluacion.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user-registry/")
public class UserRegistryController {

    private final IUserService iUserRegistryService;

    private final Util util;

    @GetMapping
    public List<UserRegistryResponseDTO> getUserList(){
        log.info("Searching list of users...");
        List<UserRegistryResponseDTO> response = new ArrayList<>();
        for(UserEntity u : iUserRegistryService.findAll()){
            response.add(
                UserRegistryResponseDTO.builder()
                            .id(u.getId().toString())
                            .name(u.getName())
                            .email(u.getEmail())
                            .created(u.getCreated())
                            .modified(u.getModified())
                            .lastLogin(u.getLastLogin())
                            .token(u.getToken())
                            .active(u.isActive())
                        .build()
            );
        }
        return response;
    }

    @PostMapping
    public UserRegistryResponseDTO userRegistry(@RequestBody UserRegistryRequestDTO userRegistry){
        log.info(String.format("userRegistryRequest: %s", util.obj2Json(userRegistry)));

        // phone list
        List<PhoneEntity> phones = new ArrayList<>();
        for(PhoneDTO p : userRegistry.getPhones()){
            phones.add(PhoneEntity.builder()
                            .contrycode(p.getContrycode())
                            .citycode(p.getCitycode())
                            .number(p.getNumber())
                    .build());
        }

        // saves the user
        UserEntity saved = iUserRegistryService.saveUser(UserEntity.builder()
                .name(userRegistry.getName())
                .email(userRegistry.getEmail())
                .password(userRegistry.getPassword())
                .phones(phones)
                .build());

        // returns the data
        return UserRegistryResponseDTO.builder()
                .id(saved.getId().toString())
                .name(saved.getName())
                .email(saved.getEmail())
                .created(saved.getCreated())
                .modified(saved.getModified())
                .lastLogin(saved.getLastLogin())
                .token(saved.getToken())
                .active(saved.isActive())
                .build();

    }

}

