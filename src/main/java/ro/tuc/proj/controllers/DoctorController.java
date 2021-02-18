package ro.tuc.ds2020.controllers;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.services.DoctorService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin()
@RequestMapping(value = "/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getDTODoctors(){
        List<DoctorDTO> dtos = doctorService.findDTODoctors();
        for(DoctorDTO dto: dtos){
            Link doctorLink = linkTo(methodOn(DoctorController.class)
                    .getDTODoctorById(dto.getId())).withRel("doctor");
            dto.add(doctorLink);

        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorDTO> getDTODoctorById(@PathVariable("id") int doctorId) {
        DoctorDTO dto = doctorService.findDoctorById(doctorId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value="/getDoctorsByGender/{gender}")
    public ResponseEntity<List<DoctorDTO>> getDTODoctorsByGender(@PathVariable("gender") String gender){
        List<DoctorDTO> dtos = doctorService.findDoctorsByGender(gender);
        for(DoctorDTO dto: dtos){
            Link doctorLink = linkTo(methodOn(DoctorController.class)
                    .getDTODoctorById(dto.getId())).withRel("doctorGender");
            dto.add(doctorLink);

        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

    @PostMapping(value = "/insert")
    public ResponseEntity<Integer> insertDTODoctor(@RequestBody DoctorDTO doctorDTO){
        int doctorId = doctorService.insertDTODoctor(doctorDTO);
        return  new ResponseEntity<>(doctorId, HttpStatus.CREATED);
    }


    @PostMapping(value = "/updateDoctorByName/{name}")
    public ResponseEntity<String> updateDoctor(@PathVariable("name") String name, @RequestBody Doctor updateDoctor) {

        String updated = doctorService.updateDTODoctor(name, updateDoctor);
        return new ResponseEntity<String>(updated, HttpStatus.ACCEPTED);

    }

    @PostMapping(value = "/deleteDoctorByName/{name}")
    public void deleteDoctor(@PathVariable("name") String name) {
        doctorService.deleteDoctorByName(name);
    }
    @PostMapping(value="/assignPatient/{doctorName}/{patientName}")
    public ResponseEntity<String> assignPatient(@PathVariable("doctorName") String doctorName, @PathVariable("patientName") String patientName){
        String assigned = doctorService.assignPatient(doctorName, patientName);
        return new ResponseEntity<String>(assigned, HttpStatus.ACCEPTED);
    }

}
