package bank.nexa.com.bankservice.transaction.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaccount", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_idstatus", nullable = false)
    private Status statusIdstatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_idproduct", nullable = false)
    private Product productIdproduct;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_idclient", nullable = false)
    private Client clientIdclient;

    @Column(name = "balance", precision = 15, scale = 2)
    private BigDecimal balance;

    @OneToMany(mappedBy = "accountIdaccount")
    private Set<Transaction> transactions = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatusIdstatus() {
        return statusIdstatus;
    }

    public void setStatusIdstatus(Status statusIdstatus) {
        this.statusIdstatus = statusIdstatus;
    }

    public Product getProductIdproduct() {
        return productIdproduct;
    }

    public void setProductIdproduct(Product productIdproduct) {
        this.productIdproduct = productIdproduct;
    }

    public Client getClientIdclient() {
        return clientIdclient;
    }

    public void setClientIdclient(Client clientIdclient) {
        this.clientIdclient = clientIdclient;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

}