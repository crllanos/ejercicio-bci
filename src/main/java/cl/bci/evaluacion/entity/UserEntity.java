package cl.bci.evaluacion.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String name;
    private String email;
    transient private String password;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<PhoneEntity> phones;
}
