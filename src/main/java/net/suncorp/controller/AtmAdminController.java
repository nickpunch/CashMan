package net.suncorp.controller;

import net.suncorp.atm.AtmState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import net.suncorp.service.AtmService;

@RestController
@RequestMapping("/net/suncorp/atm/admin")
public class AtmAdminController {
    @Autowired
    private AtmService atmService;

    @RequestMapping(value = "/getTotalRemaining", method = RequestMethod.GET)
    public AtmState getTotalAmountAvailable() {
        return atmService.getTotals();
    }

    @RequestMapping(value = "/getRemainingDenomination", method = RequestMethod.GET)
    public AtmState getTotalDenomination() {
        return atmService.getTotalDenom();
    }
}
