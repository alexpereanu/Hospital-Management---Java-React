package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.dtos.ActivityDTO;
import ro.tuc.ds2020.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {


}
