package com.gruntik.resttest.status;

public enum ResponseStatus {
    OK(0, "OK"),
    NO_NAME(1, "NAME ISN'T PRESENT"),
    NO_VALUE(2, "VALUE ISN'T PRESENT"),
    ALREADY_EXISTS(3, "ENTRY WITH THIS NAME ALREADY EXISTS"),
    NOTHING_TO_DELETE(4, "NOTHING TO DELETE BY THIS NAME"),
    NO_DATA(5, "NO DATA"),
    NO_FIRST_NUMBER(6, "THERE IS NO FIRST NAME"),
    NO_SECOND_NUMBER(7, "THERE IS NO SECOND NAME"),
    NOT_NUMBER_FIRST(8, "THE FIRST IS NOT A NUMBER"),
    NOT_NUMBER_SECOND(9, "THE SECOND IS NOT A NUMBER");

    private final int value;
    private final String description;

    ResponseStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ErrorStatus{" +
                "value=" + value +
                ", description='" + description + '\'' +
                '}';
    }
}
