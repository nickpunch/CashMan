package net.suncorp.atm;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class AtmStateTest {
    private int fifties = 5;
    private int twenties = 10;

    private AtmState testAtmState = new AtmState(fifties, twenties);
    private Map<AtmDenomination, Integer> expectedResult = new HashMap<>();

    @Test
    public void testInitialSetUpOfState() {
        expectedResult.put(AtmDenomination.FIFTIES, fifties);
        expectedResult.put(AtmDenomination.TWENTIES, twenties);
        assertNotNull(testAtmState);
        assertThat(testAtmState.getAvailableMoney(), is(expectedResult));
        assertThat(testAtmState.getMoneyTotal(), is(450));
    }

    @Test
    public void testExecuteWithdrawalCanProvideCorrectAmountFor50Only() throws Exception {
        testAtmState.executeWithdrawal(50);
        assertNotNull(testAtmState);
        assertThat(testAtmState.getMoneyTotal(), is(400));
    }

    @Test
    public void testExecuteWithdrawalCanProvideCorrectAmountTwo50Requests() throws Exception {
        testAtmState.executeWithdrawal(100);
        assertNotNull(testAtmState);
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.FIFTIES), is(3));
        assertThat(testAtmState.getMoneyTotal(), is(350));
    }

    @Test
    public void testExecuteWithdrawalCanProvideCorrectAmount20Only() throws Exception {
        testAtmState.executeWithdrawal(60);
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.FIFTIES), is(5));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.TWENTIES), is(7));
        assertThat(testAtmState.getMoneyTotal(), is(390));
    }

    @Test
    public void testExecutesWithdrawalCanProvideMultipleDenoms() throws Exception {
        testAtmState.executeWithdrawal(90);
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.FIFTIES), is(4));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.TWENTIES), is(8));
        assertThat(testAtmState.getMoneyTotal(), is(360));
    }

    @Test
    public void testExecutesWithdrawalCanProvideLastNotesInTheMachine() throws Exception {
        testAtmState.executeWithdrawal(310);
        assertThat(testAtmState.getMoneyTotal(), is(140));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.FIFTIES), is(0));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.TWENTIES), is(7));
    }

    @Test
    public void testExecutesSingle20() throws Exception {
        testAtmState.executeWithdrawal(20);
        assertThat(testAtmState.getMoneyTotal(), is(430));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.FIFTIES), is(5));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.TWENTIES), is(9));
    }

    @Test
    public void testExecutes40() throws Exception {
        testAtmState.executeWithdrawal(40);
        assertThat(testAtmState.getMoneyTotal(), is(410));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.FIFTIES), is(5));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.TWENTIES), is(8));
    }

    @Test
    public void testExecutes70() throws Exception {
        testAtmState.executeWithdrawal(70);
        assertThat(testAtmState.getMoneyTotal(), is(380));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.FIFTIES), is(4));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.TWENTIES), is(9));
    }

    @Test
    public void testExecutes80() throws Exception {
        testAtmState.executeWithdrawal(80);
        assertThat(testAtmState.getMoneyTotal(), is(370));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.FIFTIES), is(5));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.TWENTIES), is(6));
    }

    @Test
    public void testExecutes100() throws Exception {
        testAtmState.executeWithdrawal(100);
        assertThat(testAtmState.getMoneyTotal(), is(350));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.FIFTIES), is(3));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.TWENTIES), is(10));
    }

    @Test
    public void testExecutes150() throws Exception {
        testAtmState.executeWithdrawal(150);
        assertThat(testAtmState.getMoneyTotal(), is(300));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.FIFTIES), is(2));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.TWENTIES), is(10));
    }

    @Test
    public void testExecutes110() throws Exception {
        testAtmState.executeWithdrawal(110);
        assertThat(testAtmState.getMoneyTotal(), is(340));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.FIFTIES), is(4));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.TWENTIES), is(7));
    }

    @Test
    public void testExecutes210SpecialRequirement() throws Exception {
        testAtmState = new AtmState(3, 8);
        testAtmState.executeWithdrawal(210);
        assertThat(testAtmState.getMoneyTotal(), is(100));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.FIFTIES), is(0));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.TWENTIES), is(5));
    }

    @Test(expected = AtmException.class)
    public void testExecutes30SpecialRequirement() throws Exception {
        testAtmState.executeWithdrawal(30);
    }

    @Test
    public void testExecutes200NotEnough50sinTheMachine() throws Exception {
        testAtmState = new AtmState(2, 10);
        testAtmState.executeWithdrawal(200);
        assertThat(testAtmState.getMoneyTotal(), is(100));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.FIFTIES), is(0));
        assertThat(testAtmState.getAvailableMoney().get(AtmDenomination.TWENTIES), is(5));
    }

    @Test(expected = AtmException.class)
    public void testEmptyMachineGracefulFail() throws Exception {
        testAtmState = new AtmState(0, 0);
        testAtmState.executeWithdrawal(200);
    }
}