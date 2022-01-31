package com.test.projectcom.bean;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

@Component
public class ResponseError {
    @ApiModelProperty(notes = "User input field")
    private String field;
    @ApiModelProperty(notes = "Error code")
    private String code;
    @ApiModelProperty(notes = "User entered value")
    private String value;
    @ApiModelProperty(notes = "Error message")
    private String errorMsg;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
