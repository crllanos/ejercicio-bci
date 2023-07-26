package cl.bci.evaluacion.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;
    private String name;
    private String email;
    private String password;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<PhoneEntity> phones;
}
