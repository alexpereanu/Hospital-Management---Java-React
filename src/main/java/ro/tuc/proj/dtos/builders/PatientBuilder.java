package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;

public class PatientBuilder {

    public PatientBuilder(){

    }

    public static PatientDTO toPatientDTO(Patient patient){
        return new PatientDTO(patient.getId(), patient.getName(), patient.getGender(), patient.getAddress());
    }

    public static Patient toEntity(PatientDTO patientDTO){
        return new Patient(patientDTO.getId(), patientDTO.getName(), patientDTO.getGender(), patientDTO.getAddress());
    }

}
