package cl.bci.evaluacion.controller;

import cl.bci.evaluacion.dto.RegistryResponseDTO;
import cl.bci.evaluacion.dto.UserRegistryRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserRegistryController {

    @PostMapping("/user-registry")
    public RegistryResponseDTO userRegistry(@RequestBody UserRegistryRequestDTO userRegistryRequest){
        log.info(String.format("userRegistryRequest: %s", userRegistryRequest));
        return null;
    }
}
