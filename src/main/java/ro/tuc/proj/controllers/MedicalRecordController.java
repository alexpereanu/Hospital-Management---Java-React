package ro.tuc.ds2020.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.entities.MedicalRecord;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.services.MedicalRecordService;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping(value = "/medicalRecord")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService){
        this.medicalRecordService = medicalRecordService;
    }


    @GetMapping()
    public ResponseEntity<List<MedicalRecord>> getMedicalRecord(){

        List<MedicalRecord> dtos = medicalRecordService.findMedicalRecord();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "/insert/{medicationId}/{patientId}")
    public ResponseEntity<Integer> insertMedicalRecord(@RequestBody MedicalRecord medicalRecord,
                                                       @PathVariable int medicationId,
                                                       @PathVariable int patientId){

        int medicalRecordId = medicalRecordService.insertMedicalRecord(medicalRecord,medicationId,patientId);
        return  new ResponseEntity<>(medicalRecordId, HttpStatus.CREATED);
    }
}
