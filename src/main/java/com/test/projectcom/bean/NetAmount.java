package com.test.projectcom.bean;

import java.math.BigDecimal;

public class NetAmount {
    private BigDecimal grossAmount;
    private BigDecimal discountAmount;

    public NetAmount(BigDecimal grossAmount, BigDecimal discountAmount) {
        this.grossAmount = grossAmount;
        this.discountAmount = discountAmount;
    }

    public BigDecimal calculateNetAmount() throws ArithmeticException {
        return grossAmount.subtract(discountAmount).setScale(2, BigDecimal.ROUND_DOWN);
    }
}
