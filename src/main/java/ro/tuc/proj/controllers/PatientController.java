package ro.tuc.ds2020.controllers;


import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.services.PatientService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@CrossOrigin()
@RequestMapping(value = "/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> getDTOPatients(){

        List<PatientDTO> dtos = patientService.findDTOPatients();
        for(PatientDTO dto: dtos){
            Link patientLink = linkTo(methodOn(PatientController.class)
                    .getPatientById(dto.getId())).withRel("patient");
            dto.add(patientLink);

        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") int patientId) {
        Patient dto = patientService.findPatientById(patientId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/insertPatient/{doctorId}/{caregiverId}")
    public ResponseEntity<String> insertDTOPatient(@RequestBody PatientDTO patientDTO, @PathVariable("doctorId") int doctorId, @PathVariable("caregiverId") int caregiverId){

        String patientName = patientService.insertDTOPatient(patientDTO,doctorId,caregiverId);
        return  new ResponseEntity<>(patientName, HttpStatus.CREATED);
    }
    @PostMapping(value = "/updatePatientByName/{name}")
    public ResponseEntity<String> updatePatient(@PathVariable("name") String name, @RequestBody PatientDTO updatePatient ) {

        String updated = patientService.updatePatient(name, updatePatient);
        return new ResponseEntity<String>(updated, HttpStatus.ACCEPTED);

    }

    @PostMapping(value = "/deletePatientByName/{patientName}")
    public void deletePatientName(@PathVariable("patientName") String patientName){
        patientService.deletePatientByName(patientName);
    }

    @PostMapping(value = "assignTo/{patientName}/{doctorName}/{caregiverName}")
    public ResponseEntity<String> assignDoctorAndCaregiver(@PathVariable("patientName") String patientName,
                                                           @PathVariable("doctorName") String doctorName,
                                                           @PathVariable("caregiverName") String caregiverName){
        String updated = patientService.assignDoctorAndCaregiver(patientName, doctorName, caregiverName);
        return new ResponseEntity<String>(updated, HttpStatus.ACCEPTED);
    }

}
