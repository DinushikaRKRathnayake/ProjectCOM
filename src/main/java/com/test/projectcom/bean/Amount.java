package com.test.projectcom.bean;

import java.math.BigDecimal;

public abstract class Amount {
    private int numberOf49DataCards;
    private int numberOf100DataCards;

    protected abstract BigDecimal calculateAmount();

    protected Amount(int numberOf49DataCards, int numberOf100DataCards) {
        this.numberOf49DataCards = numberOf49DataCards;
        this.numberOf100DataCards = numberOf100DataCards;
    }

    public int getNumberOf49DataCards() {
        return numberOf49DataCards;
    }

    public int getNumberOf100DataCards() {
        return numberOf100DataCards;
    }
}
