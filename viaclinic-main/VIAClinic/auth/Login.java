package com.clinic.auth;

/**
 * Methods for logging into the system.
 */
public interface Login {

    /**
     * Attempts to log in with the given credentials.
     *
     * @param username the username to authenticate
     * @param password the password to authenticate
     * @return {@code true} if the credentials are correct, {@code false} otherwise
     */
    boolean login(String username, String password);

    /**
     * Logs the currently authenticated user out of the system.
     */
    void logout();
}
