package ro.tuc.ds2020.controllers;


import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.services.CaregiverService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin()
@RequestMapping(value = "/caregiver")
public class CaregiverController {

    private final CaregiverService caregiverService;

    public CaregiverController(CaregiverService caregiverService){
        this.caregiverService = caregiverService;
    }


    @GetMapping(value = "/getCaregivers")
    public ResponseEntity<List<CaregiverDTO>> getDTOCaregivers(){
        List<CaregiverDTO> dtos = caregiverService.findDTOCaregivers();
        for(CaregiverDTO dto: dtos){
            Link caregiverLink = linkTo(methodOn(CaregiverController.class)
                                .getDTOCaregiverById(dto.getId())).withRel("caregiver");
            dto.add(caregiverLink);

        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/findCaregiver/{name}")
    public ResponseEntity<Caregiver> findCaregiver(@PathVariable("name") String name) {
        Caregiver caregiver = caregiverService.findCaregiverId(name);
        return new ResponseEntity<Caregiver>(caregiver, HttpStatus.OK);

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<CaregiverDTO> getDTOCaregiverById(@PathVariable("id") int caregiverId) {
        CaregiverDTO dto = caregiverService.findCaregiverById(caregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @PostMapping(value = "/insert")
    public ResponseEntity<Integer> insertDTOCaregiver(@RequestBody CaregiverDTO caregiverDTO){
        int caregiverID = caregiverService.insertDTOCaregiver(caregiverDTO);
        return  new ResponseEntity<>(caregiverID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/deleteCaregiverByName/{name}")
    public void deleteCaregiverByName(@PathVariable("name") String nameCaregiver) {
        caregiverService.deleteCaregiverByName(nameCaregiver);
    }

    @PostMapping(value = "/updateCaregiverByName/{name}")
    public ResponseEntity<String> updateCaregiver(@PathVariable("name") String name, @RequestBody Caregiver updateCaregiver ) {

        String updated = caregiverService.updateCaregiver(name, updateCaregiver);
        return new ResponseEntity<String>(updated, HttpStatus.ACCEPTED);

    }


    @GetMapping(value="/getPatients/{caregiverName}")
    public ResponseEntity<Set<Patient>> getPatients(@PathVariable("caregiverName") String caregiverName) {
        //return new ResponseEntity<>(caregiverService.getPatients(caregiverName),HttpStatus.OK);
        Set<Patient> dtos = caregiverService.getPatients(caregiverName);
        for(Patient dto: dtos){
            Link caregiverLink = linkTo(methodOn(PatientController.class)
                    .getPatientById(dto.getId())).withRel("patient");
            dto.add(caregiverLink);

        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
