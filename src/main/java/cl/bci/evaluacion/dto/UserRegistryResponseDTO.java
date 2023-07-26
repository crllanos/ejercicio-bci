package cl.bci.evaluacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistryResponseDTO {
    private String id;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean active;
}
