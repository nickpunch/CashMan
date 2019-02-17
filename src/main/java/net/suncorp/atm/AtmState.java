package net.suncorp.atm;

import net.suncorp.dto.AtmDto;

import java.util.HashMap;
import java.util.Map;

import static net.suncorp.atm.AtmDenomination.FIFTIES;
import static net.suncorp.atm.AtmDenomination.TWENTIES;

public class AtmState {
    private AtmDto currentState;
    private Map<AtmDenomination, Integer> requestMap;
    private boolean operationComplete;

    public void executeWithdrawal(int amount) throws Exception {
        int fCount = 0;
        int tCount = 0;
        operationComplete = false;

        requestMap = new HashMap<>();

        if (currentState.calculateTotalMoneyValue() > 0) {

            if (canBeSatisfiedWithFiftiesOnly(amount)) {
                calculateDispenseFiftyDollarNotesCombinationOnly(amount, fCount);
            } else if (canBeSatisfiedWithTwentiesOnly(amount) && amount < 100) {
                calculateDispenseTwentyDollarNotesCombinationOnly(amount, tCount);
            } else {
                int divisor = FIFTIES.getNoteValue();
                double modAmount = amount % divisor;

                if (amount < 100) {
                    divisor=0;
                }

                int dispensableF = amount - divisor - (int) modAmount;
                int dispensableT = divisor + (int) modAmount;
                calculateDispenseFiftyDollarNotesCombinationOnly(dispensableF, fCount);
                calculateDispenseTwentyDollarNotesCombinationOnly(dispensableT, tCount);
            }
        } else {
            throw new Exception();
        }
    }

    private void calculateDispenseTwentyDollarNotesCombinationOnly(int amount, int tCount) throws Exception {
        int workAmount = amount;
        workAmount = workAmount - 20;
        tCount++;

        if (currentState.checkIfNotesAvailable(TWENTIES, tCount)) {
            if (workAmount == 0) {
                int twenties = currentState.getAvailableMoney().get(TWENTIES) - tCount;
                updateDollarNoteCountInTheMachineIfTransactionValid(TWENTIES, twenties);
            } else {
                calculateDispenseTwentyDollarNotesCombinationOnly(workAmount, tCount);
            }
        } else {

            if (workAmount == 0) {
                requestMap.put(TWENTIES, tCount);
                updateDollarNoteCountInTheMachineIfTransactionValid(requestMap);
            } else {
                throw new Exception();
            }

        }
    }

    private void calculateDispenseFiftyDollarNotesCombinationOnly(int amount, int fCount) throws Exception {
        int workAmount = amount;
        workAmount = workAmount - 50;
        fCount++;

        if (currentState.checkIfNotesAvailable(FIFTIES, fCount)) {
            if (workAmount == 0) {
                int fifties = currentState.getAvailableMoney().get(FIFTIES) - fCount;
                updateDollarNoteCountInTheMachineIfTransactionValid(FIFTIES, fifties);
            } else {
                calculateDispenseFiftyDollarNotesCombinationOnly(workAmount, fCount);
            }
        } else {
            if (!operationComplete) {
                requestMap.put(FIFTIES, fCount);
                calculateDispenseTwentyDollarNotesCombinationOnly(workAmount, 0);
            }
        }
    }

    private void updateDollarNoteCountInTheMachineIfTransactionValid(AtmDenomination denom, int notes)
            throws Exception {
        if (notes >= 0) {

            if (FIFTIES.equals(denom))
                currentState.dispenseFiftyDollarNotes(notes);

            if (TWENTIES.equals(denom))
                currentState.dispenseTwentyDollarNotes(notes);

            operationComplete = true;
        } else {
            //TODO Define proper exception for not having enough notes
            throw new Exception();
        }
    }

    private void updateDollarNoteCountInTheMachineIfTransactionValid(Map<AtmDenomination, Integer> requestMap) throws Exception {
        if (requestMap != null) {
            currentState.dispenseFiftyDollarNotes(requestMap.get(FIFTIES));
            currentState.dispenseTwentyDollarNotes(requestMap.get(TWENTIES));
            operationComplete = true;
        } else {
            //TODO Define proper exception for not having enough notes
            throw new Exception();
        }
    }

    private boolean canBeSatisfiedWithFiftiesOnly(int amount) {
        return amount % FIFTIES.getNoteValue() == 0;
    }

    private boolean canBeSatisfiedWithTwentiesOnly(int amount) {
        return amount % TWENTIES.getNoteValue() == 0;
    }

    public AtmState(int fiftyNotes, int twentyNotes) {
        currentState = new AtmDto(fiftyNotes, twentyNotes);
    }

    public Map<AtmDenomination, Integer> getAvailableMoney() {
        return currentState.getAvailableMoney();
    }

    public int getMoneyTotal() {
        return currentState.getMoneyTotal();
    }
}