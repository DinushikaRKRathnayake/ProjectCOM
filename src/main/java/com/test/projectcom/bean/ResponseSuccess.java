package com.test.projectcom.bean;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ResponseSuccess {
    @ApiModelProperty(notes = "Total amount without discount")
    private BigDecimal grossAmount;
    @ApiModelProperty(notes = "Payable amount after grant discount")
    private BigDecimal netAmount;

    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }
}
