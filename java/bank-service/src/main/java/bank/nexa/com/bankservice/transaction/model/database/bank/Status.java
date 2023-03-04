package bank.nexa.com.bankservice.transaction.model.database.bank;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstatus", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;


}