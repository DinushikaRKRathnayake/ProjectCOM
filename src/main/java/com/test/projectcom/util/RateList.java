package com.test.projectcom.util;

import java.math.BigDecimal;

public class RateList {

    private RateList() {
    }

    public static final double RATE_49D_CARDS = 4.50;
    public static final double RATE_49D_CARDS_EXCEED50 = 5.50;
    public static final double RATE_100D_CARDS_EXCEED10 = 10.00;
    public static final double RATE_100D_CARDS_EXCEED100 = 12.5;
    public static final BigDecimal MAX_DISCOUNT_RATE = BigDecimal.valueOf(2000.00);

}
