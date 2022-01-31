package com.test.projectcom.service;

import com.test.projectcom.bean.*;
import com.test.projectcom.repository.TransactionRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    /**
     * @param txnRequest
     * @return
     * @throws NumberFormatException
     * @throws ArithmeticException
     */
    public Response getPaymentDetails(Transaction txnRequest) throws NumberFormatException, ArithmeticException {
        //calculate payment details
        BigDecimal grossAmount = new GrossAmount(txnRequest.getNumberOf49DataCards(), txnRequest.getNumberOf100DataCards()).calculateAmount();
        BigDecimal discountAmount = new DiscountAmount(txnRequest.getNumberOf49DataCards(), txnRequest.getNumberOf100DataCards()).calculateAmount();
        BigDecimal netAmount = new NetAmount(grossAmount, discountAmount).calculateNetAmount();

        //insert payment details
        repository.saveTransactionDetails(txnRequest, grossAmount, discountAmount, netAmount);
        LogManager.getLogger(TransactionService.class).info("----------------------------------------------- Successfully save the transaction details");

        //set response
        ResponseSuccess result = new ResponseSuccess();
        result.setGrossAmount(grossAmount);
        result.setNetAmount(netAmount);
        Response response = new Response();
        response.cleanBean();
        response.setTxnStatus("Successful");
        response.setContent(result);

        return response;
    }
}
