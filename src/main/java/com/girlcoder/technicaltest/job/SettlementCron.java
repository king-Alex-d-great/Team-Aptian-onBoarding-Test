package com.girlcoder.technicaltest.job;

import com.girlcoder.technicaltest.model.Enuns.SettlementStatus;
import com.girlcoder.technicaltest.respository.AccountRepository;
import com.girlcoder.technicaltest.respository.SettlementRepository;
import com.girlcoder.technicaltest.service.SettlementService;
import com.girlcoder.technicaltest.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SettlementCron {

    @Autowired
    private SettlementService settlementService;

    @Autowired
    private SettlementRepository settlementRepository;

    @Autowired
    private AccountRepository accountRepository;



    @Async
    @Scheduled(cron = "* * * * * *")
    @Transactional
    public void SettlementResolution(){
        var settlements = settlementService.getAllPendingSettlements();
        settlements.forEach(settlement -> {
            var transaction = settlement.getTransaction();
            var sender = transaction.getSenderAccount();
            var receiver = transaction.getReceiverAccount();
            var amount = Double.parseDouble(transaction.getAmount());

            sender.setAccountBalance(sender.getAccountBalance() - amount);
            accountRepository.save(sender);

            receiver.setAccountBalance(receiver.getAccountBalance() + amount);
            accountRepository.save(receiver);

            settlement.setStatus(SettlementStatus.SUCCESSFUL);
            settlementRepository.save(settlement);
        });
    }

}
