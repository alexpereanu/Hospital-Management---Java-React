package ro.tuc.ds2020.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.services.MedicationService;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping(value = "/medication")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService){
        this.medicationService = medicationService;
    }

    @GetMapping()
    public ResponseEntity<List<Medication>> getMedication(){

        List<Medication> dtos = medicationService.findMedications();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "/insertMedication")
    public ResponseEntity<String> insertMedication(@RequestBody Medication medication){

        String med = medicationService.insertMedication(medication);
        return  new ResponseEntity<>(med, HttpStatus.CREATED);
    }

    @PostMapping(value = "/updateMedicationById/{medicationId}")
    public ResponseEntity<String> updateMedication(@PathVariable("medicationId") int medicationId, @RequestBody Medication updatedMedication) {

        String updated = medicationService.updateMedication(medicationId, updatedMedication);
        return new ResponseEntity<String>(updated, HttpStatus.ACCEPTED);

    }

    @PostMapping(value = "/deleteMedicationById/{medicationId}")
    public void deleteMedicationById(@PathVariable("medicationId") int medicationId){
        String deleted = medicationService.deleteMedicationById(medicationId);
    }
}
