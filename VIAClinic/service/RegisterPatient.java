package com.clinic.service;

import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;

import java.time.LocalDate;

/**
 * Handles registering a new patient.
 */
public class RegisterPatient {

    /** Storage for patient records. */
    private final PatientRepository repository;

    /**
     * Creates a new registration service.
     *
     * @param repository the repository used to update patients
     */
    public RegisterPatient(PatientRepository repository) {
        this.repository = repository;
    }

    /**
     * Registers a new patient in the system.
     *
     * @param name  the patient's full name
     * @param phone the patient's phone number
     * @param email the patient's email address
     * @param dob   the patient's date of birth
     * @return the updated patient
     * @throws IllegalArgumentException if any field is missing or incorrectly formatted 
     * @throws IllegalStateException    if a patient with the same email or phone is already registered 
     */
    public Patient register(String name, String phone, String email, LocalDate dob) {
        // Validation happens inside the Patient constructor.
        // If the data is invalid, an IllegalArgumentException is thrown here.
        Patient patient = new Patient(name, phone, email, dob);

        // Precondition check (ALT0): not already registered.
        if (repository.existsByEmail(patient.getEmail())) {
            throw new IllegalStateException(
                    "A patient with email '" + patient.getEmail() + "' is already registered.");
        }
        if (repository.existsByPhone(patient.getPhone())) {
            throw new IllegalStateException(
                    "A patient with phone '" + patient.getPhone() + "' is already registered.");
        }

        return repository.save(patient);
    }
}
