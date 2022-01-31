package com.test.projectcom.bean;

import java.math.BigDecimal;

public class GrossAmount extends Amount {
    public GrossAmount(int numberOf49DataCards, int numberOf100DataCards) {
        super(numberOf49DataCards, numberOf100DataCards);
    }

    @Override
    public BigDecimal calculateAmount() throws NumberFormatException, ArithmeticException {
        BigDecimal paymentOf49DataCards = BigDecimal.valueOf(0.00);
        BigDecimal paymentOf100DataCards = BigDecimal.valueOf(0.00);

        if (super.getNumberOf49DataCards() > 0) {
            paymentOf49DataCards = new BigDecimal(super.getNumberOf49DataCards() * 49);
        }
        if (super.getNumberOf100DataCards() > 0) {
            paymentOf100DataCards = new BigDecimal(super.getNumberOf100DataCards() * 100);
        }

        return paymentOf49DataCards.add(paymentOf100DataCards).setScale(2, BigDecimal.ROUND_DOWN);

    }
}
