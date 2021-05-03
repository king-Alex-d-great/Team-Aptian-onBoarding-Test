package com.girlcoder.technicaltest.respository;

import com.girlcoder.technicaltest.model.Enuns.SettlementStatus;
import com.girlcoder.technicaltest.model.Settlements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettlementRepository extends JpaRepository<Settlements, Integer> {
    List<Settlements> getAllByStatus(SettlementStatus status);
}
