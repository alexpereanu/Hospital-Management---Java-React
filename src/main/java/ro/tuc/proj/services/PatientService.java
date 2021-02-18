package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.builders.CaregiverBuilder;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.CaregiverRepository;
import ro.tuc.ds2020.repositories.DoctorRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final CaregiverRepository caregiverRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, DoctorRepository doctorRepository, CaregiverRepository caregiverRepository){
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.caregiverRepository = caregiverRepository;

    }

    public List<Patient> findPatients(){

        List<Patient> patients = patientRepository.findAll();
        return patients;

    }

    public List<PatientDTO> findDTOPatients() {
        List<Patient> patientsList = patientRepository.findAll();
        return patientsList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }

    public List<Patient> findAllMalePatients(){

        List<Patient> patients = patientRepository.findByGender("M");
        return patients.stream().collect(Collectors.toList());
    }

    public Patient findPatientById(int id){
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if(!patientOptional.isPresent()){
            LOGGER.error("Patient with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return (Patient) patientOptional.get();

    }

    public String insertDTOPatient(PatientDTO patientDTO, int doctorId, int caregiverId){

        Doctor doctor = doctorRepository.findById(doctorId);
        Caregiver caregiver = caregiverRepository.findById(caregiverId);
        Patient patient = PatientBuilder.toEntity(patientDTO);

        patient.setMyDoctor(doctor);
        patient.setMyCaregiver(caregiver);

        patient = patientRepository.save(patient);
        LOGGER.debug("DTOPatient with name {} was inserted in db", patient.getName());
        return patient.getName();

    }

    public void deleteAllPacients(){

        patientRepository.deleteAll();
        LOGGER.info("All patients have been deleted ! ");
    }


    public String deletePatientByName(String name){

        Patient patientOptional = (Patient) patientRepository.findByName(name);
        if(patientOptional == null){
            LOGGER.debug("{} wasn't in our list of patients !", name);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with name: " + name);
        }


        patientRepository.deleteInBatch(Collections.singleton(patientOptional));
        LOGGER.debug("Patient with name {} have been deleted from our db !", name);
        return name;
    }

    public String updatePatient(String name, PatientDTO updatePatient){

        Patient patientOptional = patientRepository.findByName(name);
        if(patientOptional == null){
            LOGGER.debug("{} wasn't in our list of patients !", name);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with name: " + name);
        }
        if(updatePatient.getName() != "") {
            patientOptional.setName(updatePatient.getName());
        }
        if(updatePatient.getAddress() != "") {
            patientOptional.setAddress(updatePatient.getAddress());
        }
        if(updatePatient.getGender() != "") {
            patientOptional.setGender(updatePatient.getGender());
        }

        LOGGER.info("{} patient have been updated !", name);
        patientRepository.save(patientOptional);
        return patientOptional.getName();
    }

    public String assignDoctorAndCaregiver(String patientName, String doctorName, String caregiverName){

        Patient patient = patientRepository.findByName(patientName);
        if(patient == null){
            LOGGER.debug("{} wasn't in our list of patients !", patientName);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with name: " + patientName);
        }
        Doctor doctor = doctorRepository.findByName(doctorName);
        if(doctor == null){
            LOGGER.debug("{} wasn't in our list of doctors !", doctorName);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with name: " + doctorName);
        }
        Caregiver caregiver = caregiverRepository.findByName(caregiverName);
        if(caregiver == null){
            LOGGER.debug("{} wasn't in our list of caregivers !", doctorName);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with name: " + caregiverName);
        }
        patient.setMyDoctor(doctor);
        patient.setMyCaregiver(caregiver);
        patientRepository.save(patient);
        LOGGER.info("{} has now assigned a doctor and a caregiver",patientName);
        return patient.getName();
    }
}
