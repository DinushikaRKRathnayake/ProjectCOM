package com.test.projectcom.bean;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Component
public class Transaction {

    @NotNull(message = "Value cannot be null")
    @PositiveOrZero(message = "Value must be positive integer")
    @ApiModelProperty(required = false, notes = "Number of 49 data cards user requested")
    private int numberOf49DataCards;

    @NotNull(message = "Value cannot be null")
    @PositiveOrZero(message = "Value must be positive integer")
    @ApiModelProperty(required = false, notes = "Number of 100 data cards user requested")
    private int numberOf100DataCards;

    public int getNumberOf49DataCards() {
        return numberOf49DataCards;
    }

    public void setNumberOf49DataCards(int numberOf49DataCards) {
        this.numberOf49DataCards = numberOf49DataCards;
    }

    public int getNumberOf100DataCards() {
        return numberOf100DataCards;
    }

    public void setNumberOf100DataCards(int numberOf100DataCards) {
        this.numberOf100DataCards = numberOf100DataCards;
    }

}
