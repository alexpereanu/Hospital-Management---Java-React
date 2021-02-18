package ro.tuc.ds2020.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.MedicationRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class MedicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository){
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> findMedications(){

        List<Medication> medicationsList = medicationRepository.findMedications();
        return medicationsList;
    }

    public String insertMedication(Medication medication){

        if(medication != null) {
            medicationRepository.save(medication);
            LOGGER.debug("Medication with name {} was inserted in db", medication.getMedicamentName());
            return medication.getMedicamentName();
        }
        return null;
    }

    public String updateMedication(int medicationId, Medication newMedication){

        Optional<Medication> med = medicationRepository.findById(medicationId);
        if(med != null){

            Medication medication = (Medication)med.get();

            if(newMedication.getMedicamentName() != null){
                medication.setMedicamentName(newMedication.getMedicamentName());
            }
            if(newMedication.getDosage() != 0){
                medication.setDosage(newMedication.getDosage());
            }

            if(newMedication.getSideEffects() != null){
                medication.setSideEffects(newMedication.getSideEffects());
            }

            medicationRepository.save(medication);
            return medication.getMedicamentName();
        }else{
            LOGGER.debug("ID {} wasn't in our list of medications !", medicationId);
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with name: " + medicationId);
        }
    }

    public String deleteMedicationById(int medicationId){

        Optional<Medication> med = medicationRepository.findById(medicationId);
        if(med != null){
            Medication medication = (Medication)med.get();
            medicationRepository.deleteInBatch(Collections.singleton(medication));
            return medication.getMedicamentName();
        }else{
            LOGGER.debug("ID {} wasn't in our list of medications !", medicationId);
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with name: " + medicationId);
        }
    }
}
