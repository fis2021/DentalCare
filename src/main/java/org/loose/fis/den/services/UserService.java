package org.loose.fis.den.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.den.exceptions.*;
import org.loose.fis.den.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.loose.fis.den.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;

    public static ObjectRepository<User> getUserRepository() {
        return userRepository;
    }

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String role, String name, String mail, String number) throws UsernameAlreadyExistsException, InvalidNumberException, NameTakenException {
        checkUserDoesNotAlreadyExist(username);
        checkNumber(number);
        checkName(name);
        userRepository.insert(new User(username, encodePassword(username, password), role, name, mail, number));
    }

    static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static void checkName(String name) throws NameTakenException {
        for (User user : userRepository.find()) {
            if (Objects.equals(name, user.getName()))
                throw new NameTakenException(name);
        }
    }


    public static void checkUserData(String username, String password, String role) throws UnknownUserException, IncorrectPasswordException, RoleDoesNotMatchException {
        int checkname=0;
        int checkpass=0;
        int checkrole=0;


        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                checkname=1;
                if (Objects.equals(encodePassword(username,password), user.getPassword())) {
                    checkpass=1;
                    if (Objects.equals(role, user.getRole())) {
                        checkrole = 1;
                    }
                }
            }
        }
        if (checkname == 0)
            throw new UnknownUserException();
        if (checkpass == 0)
            throw new IncorrectPasswordException();
        if (checkrole == 0)
            throw new RoleDoesNotMatchException();

    }

    private static void checkNumber(String phone) throws InvalidNumberException {
        String regex = "\\d{10}"; //regex for 10 digits
        if(phone.matches(regex)==false)
            throw new InvalidNumberException();
    }

    static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


}