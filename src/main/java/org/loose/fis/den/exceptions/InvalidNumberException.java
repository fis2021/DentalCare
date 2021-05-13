package org.loose.fis.den.exceptions;

public class InvalidNumberException extends Exception{
    public InvalidNumberException() {
        super(String.format("The phone number is invalid!"));
    }
}
