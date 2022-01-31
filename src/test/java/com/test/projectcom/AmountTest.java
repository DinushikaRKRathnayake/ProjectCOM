package com.test.projectcom;

import com.test.projectcom.bean.DiscountAmount;
import com.test.projectcom.bean.GrossAmount;
import com.test.projectcom.bean.NetAmount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootTest
public class AmountTest {
    private static ArrayList<Integer> numberOf49Cards = null, numberOf100Cards = null;
    private static ArrayList<BigDecimal> grossAmount = null, discountAmount = null, netAmount = null;

    @BeforeAll
    private static void initVariables() {
        numberOf49Cards = new ArrayList<>();
        numberOf49Cards.add(1);
        numberOf49Cards.add(1);
        numberOf49Cards.add(1);
        numberOf49Cards.add(51);
        numberOf49Cards.add(51);
        numberOf49Cards.add(51);
        numberOf49Cards.add(200);

        numberOf100Cards = new ArrayList<>();
        numberOf100Cards.add(1);
        numberOf100Cards.add(11);
        numberOf100Cards.add(101);
        numberOf100Cards.add(1);
        numberOf100Cards.add(11);
        numberOf100Cards.add(101);
        numberOf100Cards.add(100);

        grossAmount = new ArrayList<>();
        grossAmount.add(new BigDecimal("149.00"));    //(1*49)+(1*100)
        grossAmount.add(new BigDecimal("1149.00"));   //(1*49)+(11*100)
        grossAmount.add(new BigDecimal("10149.00"));  //(1*49)+(101*100)
        grossAmount.add(new BigDecimal("2599.00"));    //(51*49)+(1*100)
        grossAmount.add(new BigDecimal("3599.00"));    //(51*49)+(11*100)
        grossAmount.add(new BigDecimal("12599.00"));    //(51*49)+(101*100)
        grossAmount.add(new BigDecimal("19800.00"));    //(200*49)+(100*100)

        discountAmount = new ArrayList<>();
        discountAmount.add(new BigDecimal("4.50"));    //(1*4.50)+(1*0)
        discountAmount.add(new BigDecimal("114.50"));   //(1*4.50)+(11*10)
        discountAmount.add(new BigDecimal("1267.00"));  //(1*4.50)+(101*12.5)
        discountAmount.add(new BigDecimal("280.50"));    //(51*5.50)+(1*0)
        discountAmount.add(new BigDecimal("390.50"));    //(51*5.50)+(11*10)
        discountAmount.add(new BigDecimal("1543.00"));    //(51*5.50)+(101*12.5)
        discountAmount.add(new BigDecimal("2000.00"));    //(200*5.50)+(100*10)=2100.00

        netAmount = new ArrayList<>();
        netAmount.add(new BigDecimal("144.50"));    //(1*44.50)+(1*100)
        netAmount.add(new BigDecimal("1034.50"));   //(1*44.50)+(11*90)
        netAmount.add(new BigDecimal("8882.00"));  //(1*44.50)+(101*87.50)
        netAmount.add(new BigDecimal("2318.50"));    //(51*43.50)+(1*100)
        netAmount.add(new BigDecimal("3208.50"));    //(51*43.50)+(11*90)
        netAmount.add(new BigDecimal("11056.00"));    //(51*43.50)+(101*87.50)
        netAmount.add(new BigDecimal("17800.00"));
    }

    @Test
    void testGrossAmount() {
        System.out.println("________________________________________________________________________________________");

        for (int i = 0; i < numberOf49Cards.size(); i++) {
            System.out.println("No of 49D Cards: " + numberOf49Cards.get(i) + " | No of 100D Cards: " + numberOf100Cards.get(i));

            BigDecimal calculatedGrossAmount = new GrossAmount(numberOf49Cards.get(i), numberOf100Cards.get(i)).calculateAmount();
            Assert.isTrue(calculatedGrossAmount.compareTo(grossAmount.get(i)) == 0, "Gross Amount Invalid");
            System.out.println("Gross Amount   : " + calculatedGrossAmount);

            BigDecimal calculatedDiscountAmount = new DiscountAmount(numberOf49Cards.get(i), numberOf100Cards.get(i)).calculateAmount();
            Assert.isTrue(calculatedDiscountAmount.compareTo(discountAmount.get(i)) == 0, "Discount Amount Invalid");
            System.out.println("Discount Amount: " + calculatedDiscountAmount);

            BigDecimal calculatedNetAmount = new NetAmount(calculatedGrossAmount, calculatedDiscountAmount).calculateNetAmount();
            Assert.isTrue(calculatedNetAmount.compareTo(netAmount.get(i)) == 0, "Net Amount Invalid");
            System.out.println("Net Amount     : " + calculatedNetAmount);

            System.out.println("________________________________________________________________________________________");
        }
    }
}
