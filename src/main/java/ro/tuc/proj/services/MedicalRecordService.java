package ro.tuc.ds2020.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.builders.DoctorBuilder;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.MedicalRecord;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.DoctorRepository;
import ro.tuc.ds2020.repositories.MedicalRecordRepository;
import ro.tuc.ds2020.repositories.MedicationRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService {


    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, PatientRepository patientRepository, MedicationRepository medicationRepository){
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
        this.medicationRepository = medicationRepository;
    }

    public int insertMedicalRecord(MedicalRecord medicalRecord, int medicationId, int patientId){

        Optional<Patient> patient = patientRepository.findById(patientId);
        Optional<Medication> medication = medicationRepository.findById(medicationId);
        System.out.println(patient);
        System.out.println(medication);

        if(patient.isPresent()){
            Patient newPatient = (Patient) patient.get();
            medicalRecord.setPatient(newPatient);

        }
        if(medication.isPresent()){
            Medication newMedication = (Medication)medication.get();
            medicalRecord.setListOfMedicaments(newMedication);
        }

        medicalRecordRepository.save(medicalRecord);

        LOGGER.debug("MedicalRecord with id {} was inserted in db", medicalRecord.getId());

        return medicalRecord.getId();
        
    }

    public List<MedicalRecord> findMedicalRecord(){

        List<MedicalRecord> medicationsList = medicalRecordRepository.findAll();
        return medicationsList;
    }
}
