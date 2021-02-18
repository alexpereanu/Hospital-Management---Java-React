package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import ro.tuc.ds2020.entities.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Patient findByName(String name);

    List<Patient> findByGender(String gender);


    @Modifying
    void deleteByName(String name);



}
