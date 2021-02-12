package stardoctor;

import stardoctor.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SirenOrderHomeViewHandler {


    @Autowired
    private SirenOrderHomeRepository sirenOrderHomeRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1 (@Payload Ordered ordered) {
        try {
            if (ordered.isMe()) {
                // view 객체 생성
                SirenOrderHome sirenOrderHome = new SirenOrderHome();
                // view 객체에 이벤트의 Value 를 set 함
                sirenOrderHome.setSirenOrderId(ordered.getId());
                sirenOrderHome.setProductNm(ordered.getProductNm());
                sirenOrderHome.setQty(ordered.getQty());
                // view 레파지 토리에 save
                sirenOrderHomeRepository.save(sirenOrderHome);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayed_then_UPDATE_1(@Payload Payed payed) {
        try {
            if (payed.isMe()) {
                // view 객체 조회
                List<SirenOrderHome> sirenOrderHomeList = sirenOrderHomeRepository.findBySirenOrderId(payed.getSirenOrderId());
                for(SirenOrderHome sirenOrderHome  : sirenOrderHomeList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    sirenOrderHome.setPaymentId(payed.getId());
                    sirenOrderHome.setStatus(payed.getStatus());

                    // view 레파지 토리에 save
                    sirenOrderHomeRepository.save(sirenOrderHome);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenRefunded_then_UPDATE_2(@Payload Refunded refunded) {
        try {
            if (refunded.isMe()) {
                // view 객체 조회
                List<SirenOrderHome> sirenOrderHomeList = sirenOrderHomeRepository.findBySirenOrderId(refunded.getSirenOrderId());
                for(SirenOrderHome sirenOrderHome : sirenOrderHomeList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    sirenOrderHome.setStatus(refunded.getStatus());

                    // view 레파지 토리에 save
                    sirenOrderHomeRepository.save(sirenOrderHome);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRefunded_then_UPDATE_3(@Payload Assigned assigned) {
        try {
            if (assigned.isMe()) {
                // view 객체 조회
                List<SirenOrderHome> sirenOrderHomeList = sirenOrderHomeRepository.findBySirenOrderId(assigned.getSirenOrderId());
                for(SirenOrderHome sirenOrderHome : sirenOrderHomeList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    sirenOrderHome.setStatus(assigned.getStatus());

                    // view 레파지 토리에 save
                    sirenOrderHomeRepository.save(sirenOrderHome);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}