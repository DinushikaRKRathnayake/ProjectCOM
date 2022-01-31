package com.test.projectcom.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity {
    @Id
    @Column(name = "ID", nullable = false, unique = true, length = 32)
    private String id;

    @Column(name = "TOTAL49CARDS", nullable = false)
    private int total49Cards;

    @Column(name = "TOTAL100CARDS", nullable = false)
    private int total100Cards;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TXNDATE")
    private Date txnDate;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private GrossAmountEntity grossAmountEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private DiscountAmountEntity discountAmountEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private NetAmountEntity netAmountEntity;

    public TransactionEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotal49Cards() {
        return total49Cards;
    }

    public void setTotal49Cards(int total49Cards) {
        this.total49Cards = total49Cards;
    }

    public int getTotal100Cards() {
        return total100Cards;
    }

    public void setTotal100Cards(int total100Cards) {
        this.total100Cards = total100Cards;
    }

    public Date getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    public GrossAmountEntity getGrossAmountEntity() {
        return grossAmountEntity;
    }

    public void setGrossAmountEntity(GrossAmountEntity grossAmountEntity) {
        this.grossAmountEntity = grossAmountEntity;
    }

    public DiscountAmountEntity getDiscountAmountEntity() {
        return discountAmountEntity;
    }

    public void setDiscountAmountEntity(DiscountAmountEntity discountAmountEntity) {
        this.discountAmountEntity = discountAmountEntity;
    }

    public NetAmountEntity getNetAmountEntity() {
        return netAmountEntity;
    }

    public void setNetAmountEntity(NetAmountEntity netAmountEntity) {
        this.netAmountEntity = netAmountEntity;
    }
}
