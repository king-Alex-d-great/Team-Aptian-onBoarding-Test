package com.girlcoder.technicaltest.service.impl;

import com.girlcoder.technicaltest.model.Enuns.SettlementStatus;
import com.girlcoder.technicaltest.model.Settlements;
import com.girlcoder.technicaltest.model.Transaction;
import com.girlcoder.technicaltest.respository.SettlementRepository;
import com.girlcoder.technicaltest.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettlementServiceImpl implements SettlementService {

    @Autowired
    private SettlementRepository settlementRepository;

    @Override
    public void createSettlement(Transaction transaction) {
        var settlement = new Settlements();
        settlement.setTransaction(transaction);
        settlementRepository.save(settlement);
    }

    @Override
    public List<Settlements> getAllPendingSettlements() {
        return settlementRepository.findAll()
                .stream()
                .filter(settlement -> settlement.getStatus().equals(SettlementStatus.PENDING))
                .collect(Collectors.toList());
    }
}
