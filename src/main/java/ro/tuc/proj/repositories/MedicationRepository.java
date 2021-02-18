package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.tuc.ds2020.entities.Medication;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {

    public static final String FIND_MEDICATIONS = "SELECT id, dosage, medicament_name, side_effects FROM medication";
    @Query(value = FIND_MEDICATIONS, nativeQuery = true)
    public List<Medication> findMedications();
}
