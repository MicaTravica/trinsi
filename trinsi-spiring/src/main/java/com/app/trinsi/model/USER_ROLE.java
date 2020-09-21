package com.app.trinsi.model;

public enum USER_ROLE {
	ROLE_ADMIN ("ROLE_ADMIN"), ROLE_REGULAR("ROLE_REGULAR");

    private final String name;       

    private USER_ROLE(String s) {
        name = s;
    }

    @Override
    public String toString() {
       return this.name;
    }
}
