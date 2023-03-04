package bank.nexa.com.bankservice.transaction.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idclient", nullable = false)
    private Integer id;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "country_birthday", length = 45)
    private String countryBirthday;

    @Column(name = "middle_name", length = 45)
    private String middleName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "second_last_name", length = 45)
    private String secondLastName;

    @Column(name = "dpi", length = 45)
    private String dpi;

    @Column(name = "birthday")
    private LocalDateTime birthday;

    @Column(name = "gender", length = 45)
    private String gender;

}