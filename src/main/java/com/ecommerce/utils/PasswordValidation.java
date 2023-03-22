package com.ecommerce.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordValidation {

        public static String hashPassword(String password) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }
        public static boolean validatePassword(String enteredPassword, String storedHashedPassword) throws NoSuchAlgorithmException {
            String enteredHashedPassword = hashPassword(enteredPassword);
            return enteredHashedPassword.equals(storedHashedPassword);
        }
}
