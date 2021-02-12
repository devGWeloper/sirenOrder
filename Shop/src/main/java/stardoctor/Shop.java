package stardoctor;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Shop_table")
public class Shop {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long sirenOrderId;
    private Long paymentId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        Assigned assigned = new Assigned();
        BeanUtils.copyProperties(this, assigned);
        assigned.setStatus("assign request");
        assigned.publishAfterCommit();


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

    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    public Long getPaymentId() {return paymentId;}


}
