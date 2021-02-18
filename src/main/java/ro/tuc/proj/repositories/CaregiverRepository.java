package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Person;

import java.util.List;
import java.util.Optional;

public interface CaregiverRepository extends JpaRepository<Caregiver, Integer> {

    Caregiver findByName(String name);

    List<Caregiver> findByGender(String gender);

    Caregiver findById(int id);



    @Query(value = "SELECT c " +
            "FROM Caregiver c " +
            "WHERE c.name = :name " +
            "AND c.gender = 'M' ")
    Optional<Caregiver> findMaleCaregivers(@Param("name") String name);



}
