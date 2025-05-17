package com.example.user_service.utils;

public class UsernameUtils {
    // Convert both first name and last name to lowercase


    public static String normalizeUsername(String firstName, String lastName) {
        // Convert both first name and last name to lowercase
        String normalizedFirstName = firstName.toLowerCase();
        String normalizedLastName = lastName.toLowerCase();

        // Concatenate first name and last name with a period in between
        String normalizedUsername = normalizedFirstName + "." + normalizedLastName;

        return normalizedUsername;
    }
}
