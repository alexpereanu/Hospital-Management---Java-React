package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.ActivityDTO;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.entities.Activity;
import ro.tuc.ds2020.entities.Caregiver;

public class ActivityBuilder {

    private ActivityBuilder(){

    }

    public static ActivityDTO toActivityDTO(Activity activity){
        return new ActivityDTO(activity.getId(), activity.getStartDate(), activity.getEndDate(), activity.getActivity());
    }

    public static Activity toEntity(ActivityDTO activityDTO){
        return new Activity(activityDTO.getId(),activityDTO.getStartDate(),activityDTO.getEndDate(),activityDTO.getActivity());
    }
}
