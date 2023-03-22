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

        public static void main(String[] args) throws NoSuchAlgorithmException {
            // Example usage
            String storedHashedPassword = "4bbcb3..."; // Retrieve the stored hashed password from database
            String enteredPassword = "myPassword123"; // Retrieve the password entered by the user
            boolean isValid = validatePassword(enteredPassword, storedHashedPassword);
            if (isValid) {
                System.out.println("Password is valid");
            } else {
                System.out.println("Password is invalid");
            }
        }
}
