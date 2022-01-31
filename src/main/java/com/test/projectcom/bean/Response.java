package com.test.projectcom.bean;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

@Component
public class Response {
    @ApiModelProperty(notes = "Transaction status: Successful or Failure")
    private String txnStatus;
    @ApiModelProperty(notes = "Result in the form of ResponseSuccess or ResponseError models")
    private Object content;

    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public void cleanBean() {
        this.txnStatus = null;
        this.setContent(null);
    }

}
