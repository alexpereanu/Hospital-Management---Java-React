package ro.tuc.ds2020.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.builders.CaregiverBuilder;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.CaregiverRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CaregiverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaregiverService.class);
    private final CaregiverRepository caregiverRepository;

    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository) {
        this.caregiverRepository = caregiverRepository;
    }

    public List<CaregiverDTO> findDTOCaregivers() {
        List<Caregiver> caregiversList = caregiverRepository.findAll();
        return caregiversList.stream()
                .map(CaregiverBuilder::toCaregiverDTO)
                .collect(Collectors.toList());
    }

    public List<CaregiverDTO> findAllMaleCaregivers(){

        List<Caregiver> caregivers = caregiverRepository.findByGender("M");
        return caregivers.stream()
                .map(CaregiverBuilder::toCaregiverDTO)
                .collect(Collectors.toList());

    }

    public CaregiverDTO findCaregiverById(int id){
        Caregiver caregiverOptional = caregiverRepository.findById(id);
        if(caregiverOptional == null){
            LOGGER.error("Caregiver with id {} was not found in db", id);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);
        }
        return CaregiverBuilder.toCaregiverDTO(caregiverOptional);
    }

    public int insertDTOCaregiver(CaregiverDTO caregiverDTO){
        Caregiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);
        caregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("DTOCaregiver with name {} was inserted in db", caregiver.getName());
        return caregiver.getId();
    }

    public String insertCaregiver(Caregiver caregiver){
        caregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("DTOCaregiver with name {} was inserted in db", caregiver.getName());
        return caregiver.getName();
    }

    public Caregiver findCaregiverId(String name){
        Caregiver caregiver = caregiverRepository.findByName(name);
        if(caregiver == null){
            LOGGER.error("Caregiver with name {} was not found in db", name);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with name: " + name);
        }
        return caregiver;
    }

    public void deleteAllCaregivers(){
        caregiverRepository.deleteAll();
        LOGGER.info("All caregivers have been deleted ! ");
    }

    public void deleteCaregiverByName(String name){

        Caregiver caregiverOptional = (Caregiver) caregiverRepository.findByName(name);
       if(caregiverOptional == null){
           LOGGER.debug("{} wasn't in our list of caregivers !", name);
           throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with name: " + name);
       }
       caregiverRepository.delete(caregiverOptional);
       LOGGER.debug("Caregiver with name {} have been deleted from our db !", name);
    }

    public String updateCaregiver(String name, Caregiver updateCaregiver){

        Caregiver caregiverOptional = caregiverRepository.findByName(name);
        if(caregiverOptional == null){
            LOGGER.debug("{} wasn't in our list of caregivers !", name);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with name: " + name);
        }
        caregiverOptional.setName(updateCaregiver.getName());
        caregiverOptional.setAddress(updateCaregiver.getAddress());
        caregiverOptional.setPatients(updateCaregiver.getPatients());
        caregiverOptional.setGender(updateCaregiver.getGender());
        LOGGER.info("{} caregiver have been updated !", name);
        caregiverRepository.save(caregiverOptional);
        return caregiverOptional.getName();
    }


    public Set<Patient> getPatients(String caregiverName) {

        Caregiver caregiverOptional = caregiverRepository.findByName(caregiverName);
        if(caregiverOptional == null){
            LOGGER.debug("{} wasn't in our list of caregivers !", caregiverName);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with name: " + caregiverName);
        }
        return caregiverOptional.getPatients();
    }




}
