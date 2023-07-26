package cl.bci.evaluacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistryResponseDTO {
    private String mensaje;
    List<UserRegistryResponseDTO> UserRegistry = new ArrayList<>();
}
