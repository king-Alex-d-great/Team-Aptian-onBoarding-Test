package com.girlcoder.technicaltest.service;

import com.girlcoder.technicaltest.model.Transaction;
import com.girlcoder.technicaltest.model.dto.TransactionDto;

public interface TransactionService {

    Transaction createTransaction(TransactionDto transactionDto);
}
