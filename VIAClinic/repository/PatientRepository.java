package com.clinic.repository;

import com.clinic.model.Patient;

import java.util.List;

/**
 * Handles saving and loading patients.Later we update this to the sql database
 */
public interface PatientRepository {

    /**
     * Saves a new patient and assigns its unique identifier.
     *
     * @param patient the patient to save; must not yet have an id
     * @return the same patient instance, now with its id updated
     */
    Patient save(Patient patient);

    /**
     * Saves changes to an existing patient.
     *
     * @param patient the patient to update; must already have an id
     * @return the updated patient
     */
    Patient update(Patient patient);

    /**
     * Finds a patient by its unique identifier.
     *
     * @param id the patient id to look up
     * @return the matching patient, or {@code null} if no patient has that id
     */
    Patient findById(int id);

    /**
     * Checks whether any patient is registered with the given email address.
     *
     * @param email the email to look for
     * @return {@code true} if a patient with that email exists
     */
    boolean existsByEmail(String email);

    /**
     * Checks whether any patient is registered with the given phone number.
     *
     * @param phone the phone number to look for
     * @return {@code true} if a patient with that phone number exists
     */
    boolean existsByPhone(String phone);

    /**
     * Searches for patients by name.
     *
     * @param nameQuery the partial name to search for
     * @return a list of matching patients; empty if none match or the query is blank
     */
    List<Patient> searchByName(String nameQuery);

    /**
     * Returns all patients in the system.
     *
     * @return a list of every stored patient; empty if none are registered
     */
    List<Patient> findAll();
}
