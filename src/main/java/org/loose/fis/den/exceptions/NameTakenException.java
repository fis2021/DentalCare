package org.loose.fis.den.exceptions;

public class NameTakenException extends Exception {

    private String username;

    public NameTakenException(String username) {
        super(String.format("This name is already used!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}