package com.test.projectcom.bean;

import com.test.projectcom.util.RateList;

import java.math.BigDecimal;

public class DiscountAmount extends Amount {
    public DiscountAmount(int numberOf49DataCards, int numberOf100DataCards) {
        super(numberOf49DataCards, numberOf100DataCards);
    }

    @Override
    public BigDecimal calculateAmount() throws NumberFormatException, ArithmeticException {
        BigDecimal discountOf49DataCards = null;
        BigDecimal discountOf100DataCards = BigDecimal.valueOf(0.00);

        if (super.getNumberOf49DataCards() > 50) {
            discountOf49DataCards = BigDecimal.valueOf(super.getNumberOf49DataCards() * RateList.RATE_49D_CARDS_EXCEED50);
        } else {
            discountOf49DataCards = BigDecimal.valueOf(super.getNumberOf49DataCards() * RateList.RATE_49D_CARDS);
        }

        if (super.getNumberOf100DataCards() > 100) {
            discountOf100DataCards = discountOf100DataCards.add(BigDecimal.valueOf(super.getNumberOf100DataCards() * RateList.RATE_100D_CARDS_EXCEED100));
        } else if (super.getNumberOf100DataCards() > 10) {
            discountOf100DataCards = discountOf100DataCards.add(BigDecimal.valueOf(super.getNumberOf100DataCards() * RateList.RATE_100D_CARDS_EXCEED10));
        }

        BigDecimal grossDiscount = discountOf49DataCards.add(discountOf100DataCards);
        BigDecimal netDiscount = grossDiscount.compareTo(RateList.MAX_DISCOUNT_RATE) == 1 ? RateList.MAX_DISCOUNT_RATE : grossDiscount;

        return netDiscount.setScale(2, BigDecimal.ROUND_DOWN);

    }
}
