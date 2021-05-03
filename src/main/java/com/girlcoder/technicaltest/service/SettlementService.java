package com.girlcoder.technicaltest.service;

import com.girlcoder.technicaltest.model.Settlements;
import com.girlcoder.technicaltest.model.Transaction;

import java.util.List;

public interface SettlementService {

    void createSettlement(Transaction transaction);
    List<Settlements> getAllPendingSettlements();

}
