package com.gruntik.resttest;

public enum ErrorStatus {
    OK(0, "OK"),
    NO_NAME(1, "NAME ISN'T PRESENT"),
    NO_VALUE(2, "VALUE ISN'T PRESENT"),
    //    BAD_FORMAT_VALUE(3, "BAD FORMAT VALUE"),
    ALREADY_EXISTS(4, "ENTRY WITH THIS NAME ALREADY EXISTS"),
    NOTHING_TO_DELETE(5, "NOTHING TO DELETE BY THIS NAME"),
    NO_DATA(6, "NO DATA"),
    NO_FIRST_NUMBER(7, "THERE IS NO FIRST NAME"),
    NO_SECOND_NUMBER(8, "THERE IS NO SECOND NAME"),
    NOT_NUMBER_FIRST(9, "THE FIRST IS NOT A NUMBER"),
    NOT_NUMBER_SECOND(10, "THE SECOND IS NOT A NUMBER");


    private final int value;
    private final String description;

    ErrorStatus(int value, String reasonPhrase) {
        this.value = value;
        this.description = reasonPhrase;
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
