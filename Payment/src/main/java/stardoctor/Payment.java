package stardoctor;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long sirenOrderId;
    private String status;

    @PostPersist
    public void onPostPersist(){

        // order이 요청되었을때
        if("Payment Request".equals(this.getStatus())) {
            Payed payed = new Payed();
            BeanUtils.copyProperties(this, payed);
            payed.setStatus("Payment Complete");
            payed.publishAfterCommit();
        }

        // oderCancel이 요청 되었을때
        if("Payment Cancel Request".equals(this.getStatus())){
            Refunded refunded = new Refunded();
            BeanUtils.copyProperties(this, refunded);
            refunded.setStatus("Payment Cancel Complete");
            refunded.publishAfterCommit();
        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getSirenOrderId() {
        return sirenOrderId;
    }

    public void setSirenOrderId(Long sirenOrderId) {
        this.sirenOrderId = sirenOrderId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
