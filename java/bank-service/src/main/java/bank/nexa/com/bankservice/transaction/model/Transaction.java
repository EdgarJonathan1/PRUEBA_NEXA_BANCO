package bank.nexa.com.bankservice.transaction.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtransaction", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "operation_code_idoperation_code", nullable = false)
    private OperationCode operationCodeIdoperationCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_idaccount", nullable = false)
    private Account accountIdaccount;

    @Column(name = "account_product_idproduct", nullable = false)
    private Integer accountProductIdproduct;

    @Column(name = "amount", precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "transaction_date")
    private Instant transactionDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OperationCode getOperationCodeIdoperationCode() {
        return operationCodeIdoperationCode;
    }

    public void setOperationCodeIdoperationCode(OperationCode operationCodeIdoperationCode) {
        this.operationCodeIdoperationCode = operationCodeIdoperationCode;
    }

    public Account getAccountIdaccount() {
        return accountIdaccount;
    }

    public void setAccountIdaccount(Account accountIdaccount) {
        this.accountIdaccount = accountIdaccount;
    }

    public Integer getAccountProductIdproduct() {
        return accountProductIdproduct;
    }

    public void setAccountProductIdproduct(Integer accountProductIdproduct) {
        this.accountProductIdproduct = accountProductIdproduct;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

}