package bank.nexa.com.bankservice.transaction.model.database.bank;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "type_product")
public class TypeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtype_account", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

}