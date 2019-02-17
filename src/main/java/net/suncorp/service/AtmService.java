package net.suncorp.service;

import net.suncorp.atm.AtmState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtmService {
    private AtmState atm;

    @Autowired
    public AtmService(){
        atm = new AtmState(10, 10);
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
