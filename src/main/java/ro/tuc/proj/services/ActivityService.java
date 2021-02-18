package ro.tuc.ds2020.services;


import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.ActivityDTO;
import ro.tuc.ds2020.dtos.builders.ActivityBuilder;
import ro.tuc.ds2020.repositories.ActivityRepository;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    public int insertActivity(ActivityDTO activityDTO){
        activityRepository.save(ActivityBuilder.toEntity(activityDTO));
        return activityDTO.getId();
    }
}
