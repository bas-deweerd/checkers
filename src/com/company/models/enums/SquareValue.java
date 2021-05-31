package com.company.models.enums;

/**
 * TODO: Consider renaming square occupation to something more meaningful
 */
public enum SquareValue {
    EMPTY(0),
    WHITE_CHECKER(1),
    BLACK_CHECKER(2);

    private final int value;

    SquareValue(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
