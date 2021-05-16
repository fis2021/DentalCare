package org.loose.fis.den.model;

public class loggedUser {
    public static String actualuser;
    public static String getActualUser() {return actualuser; }
    public static void setActualUser(String name) {
        loggedUser.actualuser = name;
    }
}