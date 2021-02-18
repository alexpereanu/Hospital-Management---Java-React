package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Doctor findByName(String name);

    Doctor findById(int id);

    List<Doctor> findByGender(String gender);


}
