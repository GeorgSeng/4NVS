package entity;

import javax.persistence.*;

@Entity
@Table(name = "T_User")
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    Long id;

    String firstName;
    String lastName;
    String Phone;
}
