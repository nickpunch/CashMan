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

    @Test
    public void testMoneyDispenser() {
        testDto.dispenseFiftyDollarNotes(5);
        testDto.dispenseTwentyDollarNotes(10);
        assertThat(testDto.getAvailableMoney().get(FIFTIES), is(5));
        assertThat(testDto.getAvailableMoney().get(TWENTIES), is(10));
        assertThat(testDto.checkIfNotesAvailable(FIFTIES,2), is(true));
        assertThat(testDto.checkIfNotesAvailable(TWENTIES,25), is(false));
    }
}