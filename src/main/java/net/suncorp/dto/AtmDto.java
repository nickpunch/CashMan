package net.suncorp.dto;

import net.suncorp.atm.AtmDenomination;

import java.util.HashMap;
import java.util.Map;

public class AtmDto {
    public static final AtmDenomination FIFTIES = AtmDenomination.FIFTIES;
    public static final AtmDenomination TWENTIES = AtmDenomination.TWENTIES;

    //    @Value("#{${available.money.map}}")

    private Map<AtmDenomination, Integer> availableMoney;

    public AtmDto(int fiftyNotes, int twentyNotes) {
        availableMoney = new HashMap<>();
        setAvailableNotes(fiftyNotes, twentyNotes);
    }

    public int calculateTotalMoneyValue() {
        return availableMoney.get(FIFTIES)*FIFTIES.getNoteValue()
                + availableMoney.get(TWENTIES)* TWENTIES.getNoteValue();
    }

    private void setAvailableNotes(int fiftyNotes, int twentyNotes) {
        availableMoney.put(FIFTIES, fiftyNotes);
        availableMoney.put(TWENTIES, twentyNotes);
    }

    public void dispenseFiftyDollarNotes(int countFifty) {
        availableMoney.replace(FIFTIES, countFifty);
    }

    public void dispenseTwentyDollarNotes(int countTwenty) {
        availableMoney.replace(TWENTIES, countTwenty);
    }

    public int getMoneyTotal() {
        return calculateTotalMoneyValue();
    }

    public Map<AtmDenomination, Integer> getAvailableMoney() {
        return availableMoney;
    }

    public boolean checkIfNotesAvailable(AtmDenomination notes, int count) {
        if (availableMoney.get(notes) - count >= 0) {
            return true;
        }
        return false;
    }

    public int getNotes(AtmDenomination notes) {
        return availableMoney.get(notes);
    }
}
