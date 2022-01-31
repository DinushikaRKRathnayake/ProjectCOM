package com.test.projectcom.repository;

import com.test.projectcom.bean.Transaction;
import com.test.projectcom.model.DiscountAmountEntity;
import com.test.projectcom.model.GrossAmountEntity;
import com.test.projectcom.model.NetAmountEntity;
import com.test.projectcom.model.TransactionEntity;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public class TransactionRepository {
    @Autowired
    private TransactionPersistenceContext persistenceContext;

    @Transactional
    public void saveTransactionDetails(Transaction request, BigDecimal grossAmount, BigDecimal discountAmount, BigDecimal netAmount) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssms"));
        transactionEntity.setTotal49Cards(request.getNumberOf49DataCards());
        transactionEntity.setTotal100Cards(request.getNumberOf100DataCards());
        transactionEntity.setTxnDate(new Date());
        persistenceContext.save(transactionEntity);

        GrossAmountEntity grossAmountEntity = new GrossAmountEntity(transactionEntity, grossAmount);
        persistenceContext.save(grossAmountEntity);

        DiscountAmountEntity discountAmountEntity = new DiscountAmountEntity(transactionEntity, discountAmount);
        persistenceContext.save(discountAmountEntity);

        NetAmountEntity netAmountEntity = new NetAmountEntity(transactionEntity, netAmount);
        persistenceContext.save(netAmountEntity);

    }
}
