package net.suncorp.atm;

public enum AtmDenomination {
    FIFTIES(50), TWENTIES(20);

    private int noteValue;

    AtmDenomination(int noteValue) {
        this.noteValue = noteValue;
    }

    public int getNoteValue() {
        return noteValue;
    }
}
