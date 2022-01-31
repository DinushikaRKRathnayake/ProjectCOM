package com.test.projectcom.controller;

import com.test.projectcom.bean.Response;
import com.test.projectcom.bean.ResponseError;
import com.test.projectcom.bean.Transaction;
import com.test.projectcom.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ClassUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Payment Transaction", value = "PaymentTransaction", description = "Controller for Payment Transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    private Logger logger = LogManager.getLogger(TransactionController.class);
    private final String FAILURE_MSG = "Failure: ";

    @ApiOperation(value = "Get gross amount and net amounts for requested data card payment", response = Response.class)
    @PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getPaymentDetails(@RequestBody @Valid Transaction txnRequest) {
        Response response = null;

        try {
            logger.info("----------------------------------------------- Payment Transaction Request Started");

            //calculate and save payment details
            response = transactionService.getPaymentDetails(txnRequest);

            logger.info("----------------------------------------------- Payment Transaction Request Finished");

        } catch (Exception ex) {
            throw ex;
        }
        return response;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Response errorResponse = new Response();
        errorResponse.setTxnStatus(FAILURE_MSG + HttpStatus.BAD_REQUEST);
        errorResponse.setContent(ex.getLocalizedMessage());

        logger.error("----------------------------------------------- Error occurs: HttpMessageNotReadableException");
        logger.error(ex.getLocalizedMessage());
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        ArrayList<ResponseError> errorList = new ArrayList<>();
        StringBuilder msg = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            ResponseError error = new ResponseError();
            error.setField(fieldError.getField());
            error.setCode(fieldError.getCode());
            error.setValue(fieldError.getRejectedValue().toString());
            error.setErrorMsg(fieldError.getDefaultMessage());
            errorList.add(error);
            msg.append(fieldError.getField() + ":" + fieldError.getRejectedValue() + ":" + fieldError.getDefaultMessage() + "|");
        }

        Response errorResponse = new Response();
        errorResponse.setTxnStatus(FAILURE_MSG + HttpStatus.BAD_REQUEST);
        errorResponse.setContent(errorList);

        logger.error("----------------------------------------------- Error occurs: MethodArgumentNotValidException");
        logger.error(msg);
        return errorResponse;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleNullPointerException(NullPointerException ex) {
        Response errorResponse = new Response();
        errorResponse.setTxnStatus(FAILURE_MSG + HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setContent(ClassUtils.getShortName(ex.getClass()));

        logger.error("----------------------------------------------- Error occurs: NullPointerException", ex);
        return errorResponse;
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleArithmeticException(ArithmeticException ex) {
        Response errorResponse = new Response();
        errorResponse.setTxnStatus(FAILURE_MSG + HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setContent(ex.getLocalizedMessage());

        logger.error("----------------------------------------------- Error occurs: ArithmeticException", ex);
        return errorResponse;
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleNumberFormatException(NumberFormatException ex) {
        Response errorResponse = new Response();
        errorResponse.setTxnStatus(FAILURE_MSG + HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setContent(ex.toString());

        logger.error("----------------------------------------------- Error occurs: NumberFormatException", ex);
        return errorResponse;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleConstraintViolationException(ConstraintViolationException ex) {
        Response errorResponse = new Response();
        errorResponse.setTxnStatus(FAILURE_MSG + HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setContent(ex.getMessage());

        logger.error("----------------------------------------------- Error occurs: ConstraintViolationException", ex);
        return errorResponse;
    }

    @ExceptionHandler(SQLGrammarException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleSQLGrammarException(SQLGrammarException ex) {
        Response errorResponse = new Response();
        errorResponse.setTxnStatus(FAILURE_MSG + HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setContent(ex.getMessage());

        logger.error("----------------------------------------------- Error occurs: SQLGrammarException", ex);
        return errorResponse;
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleSQLException(SQLException ex) {
        Response errorResponse = new Response();
        errorResponse.setTxnStatus(FAILURE_MSG + HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setContent(ex.getCause().toString());

        logger.error("----------------------------------------------- Error occurs: SQLException", ex);
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    public Response handleException(Exception ex) {
        Response errorResponse = new Response();
        errorResponse.setTxnStatus("Failure ");
        errorResponse.setContent(ex.getLocalizedMessage());

        logger.error("----------------------------------------------- Error occurs: Exception");
        logger.error(ex.getMessage());
        return errorResponse;
    }
}
