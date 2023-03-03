package bank.nexa.com.bankservice.transaction.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_account_idtype_account", nullable = false)
    private TypeProduct typeAccountIdtypeAccount;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "interest_calculation", precision = 15)
    private BigDecimal interestCalculation;

    @Column(name = "interest_rate", precision = 15)
    private BigDecimal interestRate;

    @OneToMany(mappedBy = "productIdproduct")
    private Set<Account> accounts = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeProduct getTypeAccountIdtypeAccount() {
        return typeAccountIdtypeAccount;
    }

    public void setTypeAccountIdtypeAccount(TypeProduct typeAccountIdtypeAccount) {
        this.typeAccountIdtypeAccount = typeAccountIdtypeAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInterestCalculation() {
        return interestCalculation;
    }

    public void setInterestCalculation(BigDecimal interestCalculation) {
        this.interestCalculation = interestCalculation;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

}