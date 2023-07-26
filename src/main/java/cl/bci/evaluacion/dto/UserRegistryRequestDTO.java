package cl.bci.evaluacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistryRequestDTO {
    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;
}
