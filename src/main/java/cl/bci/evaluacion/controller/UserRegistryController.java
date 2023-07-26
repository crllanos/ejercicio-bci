package cl.bci.evaluacion.controller;

import cl.bci.evaluacion.dto.PhoneDTO;
import cl.bci.evaluacion.dto.RegistryResponseDTO;
import cl.bci.evaluacion.dto.UserRegistryRequestDTO;
import cl.bci.evaluacion.entity.PhoneEntity;
import cl.bci.evaluacion.entity.UserEntity;
import cl.bci.evaluacion.service.IUserRegistryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class UserRegistryController {

    @Autowired
    IUserRegistryService iUserRegistryService;

    @PostMapping("/user-registry")
    public RegistryResponseDTO userRegistry(@RequestBody UserRegistryRequestDTO userRegistry){
        log.info(String.format("userRegistryRequest: %s", userRegistry));

        List<PhoneEntity> phones = new ArrayList<>();
        for(PhoneDTO p : userRegistry.getPhones()){
            phones.add(PhoneEntity.builder()
                            .contrycode(p.getContrycode())
                            .citycode(p.getCitycode())
                            .number(p.getNumber())
                    .build());
        }

        iUserRegistryService.saveUser(UserEntity.builder()
                .name(userRegistry.getName())
                .email(userRegistry.getEmail())
                .password(userRegistry.getPassword())
                .phones(phones)
                .build());

        return null;
    }
}
