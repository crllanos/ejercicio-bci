package cl.bci.evaluacion.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneEntity {
    private Integer id;
    private Integer number;
    private Integer citycode;
    private Integer contrycode;
}
