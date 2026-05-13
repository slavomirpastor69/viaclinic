package com.clinic.service;

import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

/**
 Handles editing a patient's information.
 */
public class EditPatient {

    /** Storage for patient records. */
    private final PatientRepository repository;

    /**
     * Sets up the edit service.
     *
     * @param repository the repository used to read and write patients
     */
    public EditPatient(PatientRepository repository) {
        this.repository = repository;
    }

    /**
     * Searches for patients by name.
     *
     * @param nameQuery a partial name to search for 
     * @return a list of matching patients; empty if nothing matches
     */
    public List<Patient> searchPatients(String nameQuery) {
        return repository.searchByName(nameQuery);
    }

    /**
     * Loads a patient record by its id.
     *
     * @param patientId the id of the patient to load
     * @return the matching patient
     * @throws NoSuchElementException if no patient has the given id 
     */
    public Patient getPatient(int patientId) {
        Patient patient = repository.findById(patientId);
        if (patient == null) {
            throw new NoSuchElementException(
                    "Patient record with id " + patientId + " cannot be found.");
        }
        return patient;
    }

    /**
     * Applies edits to an existing patient record.
     *
     * @param patientId the id of the patient to edit
     * @param name      the new name
     * @param phone     the new phone number
     * @param email     the new email address
     * @param dob       the new date of birth
     * @return the updated patient
     * @throws NoSuchElementException   if no patient has the given id 
     * @throws IllegalArgumentException if any field is missing or invalid 
     * @throws IllegalStateException    if the new email or phone already belongs to another patient
     */
    public Patient editPatient(int patientId,
                               String name,
                               String phone,
                               String email,
                               LocalDate dob) {
        Patient patient = repository.findById(patientId);
        if (patient == null) {
            throw new NoSuchElementException(
                    "Patient record with id " + patientId + " cannot be found.");
        }

        // Validates all new field values together.
        Patient.validate(name, phone, email, dob);

        // Make sure the new email/phone don't clash with an another patient.
        List<Patient> all = repository.findAll();
        for (int i = 0; i < all.size(); i++) {
            Patient other = all.get(i);
            if (other.getId().equals(patientId)) {
                continue;
            }
            if (email.equalsIgnoreCase(other.getEmail())) {
                throw new IllegalStateException(
                        "Another patient already uses email '" + email + "'.");
            }
            if (phone.equals(other.getPhone())) {
                throw new IllegalStateException(
                        "Another patient already uses phone '" + phone + "'.");
            }
        }

        // Setters perform individual validation as well, acting as a safety net.
        patient.setName(name);
        patient.setPhone(phone);
        patient.setEmail(email);
        patient.setDateOfBirth(dob);

        return repository.update(patient);
    }
}
