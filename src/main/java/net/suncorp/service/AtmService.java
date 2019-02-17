package net.suncorp.service;

import net.suncorp.atm.AtmState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class AtmService {
    private AtmState atm;

    @Value("${fifty.dollar.notes:10}")
    private int fifties;

    @Value("${twenty.dollar.notes:20}")
    private int twenties;

    @Autowired
    public AtmService(){
        atm = new AtmState(fifties, twenties);
    }
    public AtmState withdrawMoney(int amount) throws Exception {
        atm.executeWithdrawal(amount);
        return atm;
    }

    public AtmState getTotals() {
        return atm;
    }

    public AtmState getTotalDenom() {
        return atm;
    }
}
