package com.clinic.model;

import java.util.ArrayList;

/**
 * Represents a receptionist actor in the clinic system.
 * */
    public class Receptionist {

    /** Unique id of the receptionist. */
    private int id;

    /** Username used to log into the system. */
    private String username;

    /**
     * Creates a new receptionist. All fields are validated.
     *
     * @param id       unique identifier
     * @param username username used to log in
     * @throws IllegalArgumentException if any field is missing or invalid
     */
    public Receptionist(int id, String username) {
        validate(id, username);
        this.id = id;
        this.username = username.trim();
    }

    /**
     * Validates all receptionist fields at once. Throws a single
     * {@link IllegalArgumentException} whose message lists every problem found.
     *
     * @param id       the receptionist id
     * @param username the username
     * @throws IllegalArgumentException if any field is missing or invalid
     */
    public static void validate(int id, String username) {
        ArrayList<String> errors = new ArrayList<>();

        if (id <= 0) {
            errors.add("Receptionist id must be positive");
        }

        if (username == null || username.isBlank()) {
            errors.add("Username is required");
        } else if (username.trim().length() < 3) {
            errors.add("Username must be at least 3 characters");
        }

        if (!errors.isEmpty()) {
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < errors.size(); i++) {
                if (i > 0) {
                    message.append("; ");
                }
                message.append(errors.get(i));
            }
            throw new IllegalArgumentException(message.toString());
        }
    }

    /**
     * Returns the receptionist's unique id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the receptionist's username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Updates the receptionist's id after validating it.
     *
     * @param id the new id; must be positive
     * @throws IllegalArgumentException if the id is not positive
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Receptionist id must be positive");
        }
        this.id = id;
    }

    /**
     * Updates the receptionist's username after validating it.
     *
     * @param username the new username
     * @throws IllegalArgumentException if the username is missing or too short
     */
    public void setUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (username.trim().length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters");
        }
        this.username = username.trim();
    }
}
