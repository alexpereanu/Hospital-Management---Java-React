package ro.tuc.ds2020.services;

import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.ActivityDTO;

import java.nio.charset.StandardCharsets;
import java.time.Period;
import java.util.concurrent.TimeUnit;

@Service
public class RabbitMQListner implements MessageListener {


    public void onMessage(Message message) {

        //System.out.println("Consuming Message - " + message.toString());

       /* byte[] s = message.getBody();
        String str = new String(s, StandardCharsets.UTF_8);
        String[] tokens = str.split("/");

        String id = tokens[0];
        String startDate = tokens[1];
        String endDate = tokens[2];
        String activity = tokens[3];

        System.out.println("Consuming: id = " + id + " startDate = " + startDate + " endDate = " + endDate + " activity = " + activity);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/

        Gson gson = new Gson();
        String result = message.toString();
        ActivityDTO activityDTO = gson.fromJson(result,ActivityDTO.class);

        long hours = TimeUnit.MILLISECONDS.toHours(activityDTO.getEndDate() - activityDTO.getStartDate());

        if(activityDTO.getActivity().equals("Sleeping") && hours > 7){
            System.out.println(activityDTO.getId() + activityDTO.getActivity());
        }


    }

}