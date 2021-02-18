package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ParameterValidationException;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.builders.CaregiverBuilder;
import ro.tuc.ds2020.dtos.builders.DoctorBuilder;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.DoctorRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, PatientRepository patientRepository){
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<DoctorDTO> findDTODoctors() {
        List<Doctor> doctorsList = doctorRepository.findAll();
        return doctorsList.stream()
                .map(DoctorBuilder::toDoctorDTO)
                .collect(Collectors.toList());
    }

    public List<DoctorDTO> findDoctorsByGender(String gender){

        List<String> error = new ArrayList<>();
        error.add("This gender doesn' exist !");

        if(gender.equals("M")||gender.equals("F")) {
            List<Doctor> doctors = doctorRepository.findByGender(gender);
            return doctors.stream()
                    .map(DoctorBuilder::toDoctorDTO)
                    .collect(Collectors.toList());
        }
        else{
            throw new ParameterValidationException(gender,error);
        }
    }

    public DoctorDTO findDoctorById(int id){
        Doctor doctor = doctorRepository.findById(id);
        if(doctor == null){
            LOGGER.error("Doctor with id {} was not found in db", id);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + id);
        }
        return DoctorBuilder.toDoctorDTO(doctor);
    }

    public int insertDTODoctor(DoctorDTO doctorDTO){
        Doctor doctor = DoctorBuilder.toEntity(doctorDTO);
        doctor = doctorRepository.save(doctor);
        LOGGER.debug("DoctorDTO with name {} was inserted in db", doctor.getName());
        return doctor.getId();
    }

    public String updateDTODoctor(String name, Doctor doctorUpdate){

        Doctor doctor = doctorRepository.findByName(name);
        if(doctor == null){
            LOGGER.debug("{} wasn't in our list of doctor !", name);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with name: " + name);
        }

        if(doctorUpdate.getName() != ""){
            doctor.setName(doctorUpdate.getName());
        }

        if(doctorUpdate.getAddress() != ""){
            doctor.setAddress(doctorUpdate.getAddress());
        }

        if(doctorUpdate.getBirthDate() != null){
            doctor.setBirthDate(doctorUpdate.getBirthDate());
        }

        if(doctorUpdate.getGender() != ""){
            doctor.setGender(doctorUpdate.getGender());
        }

        if(doctorUpdate.getPatients() != null){
            doctor.setPatients(doctorUpdate.getPatients());
        }

        doctorRepository.save(doctor);
        LOGGER.info("{} doctor have been updated !", name);
        return doctor.getName();
    }

    public String assignPatient(String doctorName, String patientName){

        Patient patient = patientRepository.findByName(patientName);
        if(patient == null){
            LOGGER.debug("{} wasn't in our list of patients and can't be assigned to {} !",patientName, doctorName);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with name: " + patientName);
        }

        Doctor doctor = doctorRepository.findByName(doctorName);
        if(doctor == null){
            LOGGER.debug("{} wasn't in our list of doctors !",doctorName);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with name: " + doctorName);
        }

        Set<Patient> patients = doctor.getPatients();
        patients.add(patient);
        doctor.setPatients(patients);
        doctorRepository.save(doctor);

        patient.setMyDoctor(doctor);
        patientRepository.save(patient);

        return doctor.getName();
    }

    public void deleteDoctorByName(String name){

        Doctor doctorOptional = doctorRepository.findByName(name);
        if(doctorOptional == null){
            LOGGER.debug("{} wasn't in our list of doctors !", name);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with name: " + name);
        }
        doctorRepository.delete(doctorOptional);
        LOGGER.debug("Caregiver with name {} have been deleted from our db !", name);
    }
   /* public String makeMedicationPlan(String doctorName, String patientName, medicalRecord, List<Medication>){

        Doctor doctor = doctorRepository.findByName(doctorName);
        if(doctor == null){
            LOGGER.debug("{} wasn't in our list of doctors !",doctorName);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with name: " + doctorName);
        }

        Patient patient = patientRepository.findByName(patientName);
        if(patient == null){
            LOGGER.debug("{} wasn't in our list of patients and can't be assigned to {} !",patientName, doctorName);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with name: " + patientName);
        }


    }
    */


}
