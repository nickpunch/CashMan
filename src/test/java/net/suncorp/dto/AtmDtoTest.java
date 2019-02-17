package net.suncorp.dto;

import net.suncorp.atm.AtmDenomination;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AtmDtoTest {
    public static final AtmDenomination FIFTIES = AtmDenomination.FIFTIES;
    public static final AtmDenomination TWENTIES = AtmDenomination.TWENTIES;
    public AtmDto testDto = new AtmDto(10, 20);;

    @Test
    public void testInitialSetUp() {
        int moneyTotal = testDto.getMoneyTotal();
        assertNotNull(testDto);
        assertThat(testDto.getAvailableMoney().get(FIFTIES), is(10));
        assertThat(testDto.getAvailableMoney().get(TWENTIES), is(20));
        assertThat(moneyTotal, is(900));
    }
}