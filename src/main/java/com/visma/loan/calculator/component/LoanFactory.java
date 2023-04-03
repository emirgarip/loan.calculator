package com.visma.loan.calculator.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.visma.loan.calculator.model.LoanType;
import com.visma.loan.calculator.strategy.LoanStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanFactory {
    private Map<LoanType, LoanStrategy> strategies;

    @Autowired
    public LoanFactory(Set<LoanStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public LoanStrategy findStrategy(LoanType strategyName) {
        return strategies.get(strategyName);
    }
    private void createStrategy(Set<LoanStrategy> strategySet) {
        strategies = new HashMap<>();
        strategySet.forEach(
                strategy ->strategies.put(strategy.getLoanStrategy(), strategy));
    }
}
