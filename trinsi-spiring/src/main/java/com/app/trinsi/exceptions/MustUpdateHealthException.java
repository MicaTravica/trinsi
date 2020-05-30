package com.app.trinsi.exceptions;

public class MustUpdateHealthException extends Exception {

    public MustUpdateHealthException() {
        super("You must update your health data!");
    }
}
