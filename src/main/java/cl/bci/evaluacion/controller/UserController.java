package cl.bci.evaluacion.controller;

import cl.bci.evaluacion.dto.PhoneDTO;
import cl.bci.evaluacion.dto.UserRequestDTO;
import cl.bci.evaluacion.dto.UserResponseDTO;
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
public class UserController {

    private final IUserService iUserService;

    private final Util util;

    @GetMapping
    public List<UserResponseDTO> getUserList(){
        log.info("Searching list of users...");
        List<UserResponseDTO> response = new ArrayList<>();
        for(UserEntity u : iUserService.findAll()){
            response.add(userEntity2DTO(u));
        }
        return response;
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable String id){
        log.info(String.format("Searching users id %s...", id));
        return userEntity2DTO(iUserService.findById(id));
    }


    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequest){
        log.info(String.format("Creating user: %s", util.obj2Json(userRequest)));

        // phone list
        List<PhoneEntity> phones = new ArrayList<>();
        for(PhoneDTO p : userRequest.getPhones()){
            phones.add(PhoneEntity.builder()
                            .contrycode(p.getContrycode())
                            .citycode(p.getCitycode())
                            .number(p.getNumber())
                    .build());
        }

        // saves the user
        UserEntity saved = iUserService.saveUser(UserEntity.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phones(phones)
                .build());

        // returns the data
        return userEntity2DTO(saved);

    }

    //@PutMapping
    //public UserRegistryResponseDTO updateUser()



    private UserResponseDTO userEntity2DTO(UserEntity entity){
        return UserResponseDTO.builder()
                .id(entity.getId().toString())
                .name(entity.getName())
                .email(entity.getEmail())
                .created(entity.getCreated())
                .modified(entity.getModified())
                .lastLogin(entity.getLastLogin())
                .token(entity.getToken())
                .active(entity.isActive())
                .build();
    }
}

