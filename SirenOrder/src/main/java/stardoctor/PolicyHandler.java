package stardoctor;

import stardoctor.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    SirenOrderRepository sirenOrderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverAssigned_UpdateStatus(@Payload Assigned assigned){

        if(assigned.isMe()){

            Optional<SirenOrder> orderOptional = sirenOrderRepository.findById(assigned.getSirenOrderId());
            SirenOrder sirenOrder = orderOptional.get();
            System.out.println(assigned.getStatus());
            sirenOrder.setStatus("Order Assigned");

            sirenOrderRepository.save(sirenOrder);

            System.out.println("##### listener  UpdateStatus: " + assigned.toJson());
        }
    }

}
