package ro.tuc.ds2020.services;


import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dtos.ActivityDTO;
import org.springframework.messaging.simp.SimpMessagingTemplate;


import java.util.concurrent.TimeUnit;

@Component
public class Receiver {

    private final ActivityService activityService;
    private final SimpMessagingTemplate webSocket;

    public Receiver(ActivityService activityService, SimpMessagingTemplate webSocket){
        this.activityService = activityService;
        this.webSocket = webSocket;
    }

    @RabbitListener(queues = "alex")
    public void connect(String message) throws InterruptedException {


        Gson gson = new Gson();
        String result = message.toString();
        ActivityDTO activityDTO = gson.fromJson(result,ActivityDTO.class);

        long hours = TimeUnit.MILLISECONDS.toHours(activityDTO.getEndDate() - activityDTO.getStartDate());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(activityDTO.getEndDate() - activityDTO.getStartDate());

        //rule1
        if((activityDTO.getActivity().equals("Sleeping") || activityDTO.getActivity().equals("Sleeping\t")) && hours > 7){

            activityDTO.setAnomaly(true);
            webSocket.convertAndSend("/topic/message", "Pacientul a dormit mai mult de 7 ore ! ");
            System.out.println("R1 --> hours = " + hours + " Sleeping");

        }

        //rule2
        if((activityDTO.getActivity().equals("Leaving") || activityDTO.getActivity().equals("Leaving\t")) && hours > 5){

            activityDTO.setAnomaly(true);
            webSocket.convertAndSend("/topic/message", "Pacientul s-a plimbat mai mult de 5 ore ! ");
            System.out.println("R2 --> hours = " + hours + " Leaving");
        }

        if(((activityDTO.getActivity().equals("Grooming") || activityDTO.getActivity().equals("Grooming\t")) ||
                (activityDTO.getActivity().equals("Toileting") || activityDTO.getActivity().equals("Toileting\t"))||
            (activityDTO.getActivity().equals("Showering") || activityDTO.getActivity().equals("Showering\t")))
        && minutes > 30){

            activityDTO.setAnomaly(true);
            webSocket.convertAndSend("/topic/message", "Pacientul a stat la baie mai mult de 30 de minute ! ");
            System.out.println("R3 --> hours = " + hours + " " + activityDTO.getActivity());
        }

        Thread.sleep(100);
        if(activityDTO.isAnomaly()) {
            activityService.insertActivity(activityDTO);
        }

    }
}
