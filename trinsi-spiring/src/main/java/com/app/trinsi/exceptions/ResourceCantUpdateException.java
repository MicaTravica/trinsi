package com.app.trinsi.exceptions;

public class ResourceCantUpdateException extends Exception {

    public ResourceCantUpdateException(String resource) {
        super(String.format("%s cant be updated!", resource));
    }
}
