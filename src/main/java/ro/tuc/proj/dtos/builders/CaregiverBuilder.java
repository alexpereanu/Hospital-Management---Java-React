package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Person;

public class CaregiverBuilder {

    private CaregiverBuilder(){

    }

    public static CaregiverDTO toCaregiverDTO(Caregiver caregiver){
        return new CaregiverDTO(caregiver.getId(), caregiver.getName(), caregiver.getGender());
    }

    public static Caregiver toEntity(CaregiverDTO caregiverDTO){
        return new Caregiver(caregiverDTO.getId(), caregiverDTO.getName(), caregiverDTO.getGender());
    }

}
