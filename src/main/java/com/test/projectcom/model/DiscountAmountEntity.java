package com.test.projectcom.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DISCOUNTAMOUNT")
public class DiscountAmountEntity {
    @Id
    @Column(name = "TXNID")
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "TXNID")
    private TransactionEntity txn;

    @Column(name = "AMOUNT", precision = 22, scale = 2)
    private BigDecimal amount;

    public DiscountAmountEntity(TransactionEntity txn, BigDecimal amount) {
        this.txn = txn;
        this.amount = amount;
    }

    public TransactionEntity getTxn() {
        return txn;
    }

    public void setTxn(TransactionEntity txn) {
        this.txn = txn;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
