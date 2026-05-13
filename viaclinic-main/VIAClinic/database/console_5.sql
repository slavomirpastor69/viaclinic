create schema VIAClinic
create table users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    day_of_birth DATE,
    age INT,
    gender VARCHAR(20),
    phone_num VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255)
);
CREATE TABLE patient
(
    patient_id INT PRIMARY KEY,
    last_visit DATE,
    medical_notes TEXT,
    cpr VARCHAR(50),

    CONSTRAINT fk_patient_user
        FOREIGN KEY (patient_id)
            REFERENCES users (id)
            ON DELETE CASCADE
);
CREATE TABLE doctor (
    doctor_id INT PRIMARY KEY,
    specialization VARCHAR(100),

    CONSTRAINT fk_doctor_user
        FOREIGN KEY (doctor_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);
CREATE TABLE receptionist (
    receptionist_id INT PRIMARY KEY,

    CONSTRAINT fk_receptionist_user
        FOREIGN KEY (receptionist_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);
CREATE TABLE appointment (
    appointment_id SERIAL PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    appointment_date TIMESTAMP,
    status VARCHAR(50),
    notes TEXT,

    CONSTRAINT fk_appointment_patient
        FOREIGN KEY (patient_id)
        REFERENCES patient(patient_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_appointment_doctor
        FOREIGN KEY (doctor_id)
        REFERENCES doctor(doctor_id)
        ON DELETE CASCADE
);
CREATE TABLE chat (
    chat_id SERIAL PRIMARY KEY,
    patient_id INT,
    doctor_id INT,

    CONSTRAINT fk_chat_patient
        FOREIGN KEY (patient_id)
        REFERENCES patient(patient_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_chat_doctor
        FOREIGN KEY (doctor_id)
        REFERENCES doctor(doctor_id)
        ON DELETE CASCADE
);
CREATE TABLE message (
    message_id SERIAL PRIMARY KEY,
    chat_id INT,
    sender VARCHAR(100),
    message_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    text TEXT,

    CONSTRAINT fk_message_chat
        FOREIGN KEY (chat_id)
        REFERENCES chat(chat_id)
        ON DELETE CASCADE
);