package net.suncorp.controller;

import net.suncorp.atm.AtmState;
import net.suncorp.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/net/suncorp/atm")
public class AtmController {

    @Autowired
    private AtmService atmService;

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public AtmState withdrawMoney(@RequestParam(name = "amount") final int amount) throws Exception{
        return atmService.withdrawMoney(amount);
    }
}