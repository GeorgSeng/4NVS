package entity;

import javax.persistence.*;
import java.time.LocalDateTime;

public class LoginLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    boolean allowed;
    LocalDateTime loginTime;

    @ManyToOne()
    User user;

}
