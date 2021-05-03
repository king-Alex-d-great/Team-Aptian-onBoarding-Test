package com.girlcoder.technicaltest.service.impl;

import com.girlcoder.technicaltest.model.Transaction;
import com.girlcoder.technicaltest.model.dto.TransactionDto;
import com.girlcoder.technicaltest.respository.TransactionRepository;
import com.girlcoder.technicaltest.service.AccountService;
import com.girlcoder.technicaltest.service.SettlementService;
import com.girlcoder.technicaltest.service.TransactionService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SettlementService settlementService;

    @Autowired
    private AccountService accountService;

    @SneakyThrows
    @Override
    public Transaction createTransaction(TransactionDto transactionDto) {
        var transaction = new Transaction();

        transaction.setTransactionDetails("Transaction between "+ transactionDto.getTo() + " and " + transactionDto.getFrom());
        var senderAccount = accountService.getAccountByAccountNumber(transactionDto.getFrom());
        var receiverAccount = accountService.getAccountByAccountNumber(transactionDto.getTo());

        if (senderAccount.getAccountBalance() < transactionDto.getAmount()){
            throw new Exception("Insufficient Funds");
        }
        transaction.setSenderAccount(senderAccount);
        transaction.setReceiverAccount(receiverAccount);
        transaction.setAmount(transactionDto.getAmount().toString());
        transaction.setTransactionReference(generateReference());

        transactionRepository.save(transaction);
        settlementService.createSettlement(transaction);
        return transaction;
    }

    private String generateReference(){
        String generatedReference = RandomStringUtils.randomAlphabetic(10);
        if (transactionRepository.existsByTransactionReference(generatedReference)){
            generateReference();
        }
        return generatedReference;
    }
}
