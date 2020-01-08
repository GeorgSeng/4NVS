package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_User")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue( strategy = GenerationType.AUTO)
    Long id;

    String firstName;
    String lastName;
    String Phone;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    List<LoginLog> loginLogsList = new ArrayList<>();
}
