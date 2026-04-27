Create schema Patients;
create table patinet_info(
    full_name varchar(150),
    patient_id char(9) primary key unique ,
    age varchar(3),
    contact_number char(11),
    patient_last_visit_date date,
    gender varchar check(gender in ('male','female','other')),
    email varchar(100)
)