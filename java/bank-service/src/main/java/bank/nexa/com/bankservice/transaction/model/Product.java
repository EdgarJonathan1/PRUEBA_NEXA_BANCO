package bank.nexa.com.bankservice.transaction.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idtype_account", nullable = false)
    private TypeProduct idtypeAccount;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "interest_calculation", precision = 15)
    private BigDecimal interestCalculation;

    @Column(name = "interest_rate", precision = 15)
    private BigDecimal interestRate;


}