package com.clinic.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

/**
 * Represents a patient in the clinic.
 */
public class Patient {

    /** Unique identifier assigned by the repository; {@code null} */
    private Integer id;

    /** Full name of the patient. */
    private String name;

    /** Contact phone number. */
    private String phone;

    /** Contact email address. */
    private String email;

    /** Date of birth. */
    private LocalDate dateOfBirth;

    /**
     * Creates a new patient. All fields are validated.
     *
     * @param name        full name of the patient
     * @param phone       contact phone number
     * @param email       contact email address
     * @param dateOfBirth date of birth
     * @throws IllegalArgumentException if any field is missing or invalid
     */
    public Patient(String name, String phone, String email, LocalDate dateOfBirth) {
        validate(name, phone, email, dateOfBirth);
        this.name = name.trim();
        this.phone = phone.trim();
        this.email = email.trim();
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Validates all patient fields at once. Throws a single
     * {@link IllegalArgumentException} whose message lists every problem
     * found so the UI can highlight all invalid fields together.
     *
     * @param name  the patient's name; must be non-blank
     * @param phone the phone number; must contain only digits, spaces, dashes, and an optional leading +
     * @param email the email address; must contain "@" and "."
     * @param dob   the date of birth; must be non-null and not in the future
     * @throws IllegalArgumentException if any field is missing or incorrectly formatted
     */
    public static void validate(String name, String phone, String email, LocalDate dob) {
        ArrayList<String> errors = new ArrayList<>();

        if (name == null || name.isBlank()) {
            errors.add("Name is required");
        }

        if (phone == null || phone.isBlank()) {
            errors.add("Phone is required");
        } else if (!isValidPhone(phone)) {
            errors.add("Phone format is invalid");
        }

        if (email == null || email.isBlank()) {
            errors.add("Email is required");
        } else if (!isValidEmail(email)) {
            errors.add("Email format is invalid");
        }

        if (dob == null) {
            errors.add("Date of birth is required");
        } else if (dob.isAfter(LocalDate.now())) {
            errors.add("Date of birth cannot be in the future");
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
     * Checks whether the given phone number has a valid format.
     *
     * @param phone the phone number to check
     * @return {@code true} if the phone number is valid
     */
    private static boolean isValidPhone(String phone) {
        if (phone.length() < 6 || phone.length() > 20) {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            boolean isDigit = (c >= '0' && c <= '9');
            boolean isPlus = (c == '+' && i == 0);
            boolean isSpace = (c == ' ');
            boolean isDash = (c == '-');
            if (!isDigit && !isPlus && !isSpace && !isDash) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether the given email has a valid format.
     * @param email the email address to check
     * @return {@code true} if the email format is valid
     */
    private static boolean isValidEmail(String email) {
        int atIndex = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        if (atIndex <= 0 || atIndex != lastAt) {
            return false;
        }
        int dotIndex = email.indexOf('.', atIndex);
        if (dotIndex == -1 || dotIndex == email.length() - 1) {
            return false;
        }
        return true;
    }

    /**
     * Returns the unique patient id.
     *
     * @return the patient id, or {@code null}.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns the patient's full name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the patient's phone number.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Returns the patient's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the patient's date of birth.
     *
     * @return the date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Returns the patient's age.
     *
     * @return the age , or {@code 0} if date of birth is {@code null}
     */
    public int getAge() {
        if (dateOfBirth == null) return 0;
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * Assigns the unique id.
     *
     * @param id the id to assign
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Updates the patient's name after validating it.
     *
     * @param name the new name
     * @throws IllegalArgumentException if the name is missing or blank
     */
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        this.name = name.trim();
    }

    /**
     * Updates the patient's phone number after validating it.
     *
     * @param phone the new phone number
     * @throws IllegalArgumentException if the phone number is missing or invalid
     */
    public void setPhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone is required");
        }
        if (!isValidPhone(phone)) {
            throw new IllegalArgumentException("Phone format is invalid");
        }
        this.phone = phone.trim();
    }

    /**
     * Updates the patient's email address after validating it.
     *
     * @param email the new email address
     * @throws IllegalArgumentException if the email is missing or invalid
     */
    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email format is invalid");
        }
        this.email = email.trim();
    }

    /**
     * Updates the patient's date of birth after validating it.
     *
     * @param dateOfBirth the new date of birth
     * @throws IllegalArgumentException if the date is {@code null} or in the future
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth is required");
        }
        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Compares two patients by their unique id.
     *
     * @return {@code true} if the objects represent the same valid patient
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient other = (Patient) o;
        return id != null && id.equals(other.id);
    }

    /**
     * {@inheritDoc}
     *
     * @return representation of the patient
     */
    @Override
    public String toString() {
        return String.format("Patient{id=%s, name='%s', phone='%s', email='%s', dob=%s, age=%d}",
                id, name, phone, email, dateOfBirth, getAge());
    }
}
