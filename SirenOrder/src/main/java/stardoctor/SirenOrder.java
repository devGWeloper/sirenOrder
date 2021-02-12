package stardoctor;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="SirenOrder_table")
public class SirenOrder {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String productNm;
    private Integer qty;
    private String status;

    @PrePersist
    public void onPrePersist(){
        try {
            Thread.currentThread().sleep((long) (800 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PostPersist
    public void onPostPersist(){
        Ordered ordered = new Ordered();
        BeanUtils.copyProperties(this, ordered);
        ordered.publishAfterCommit();

        stardoctor.external.Payment payment = new stardoctor.external.Payment();
        // mappings goes here
        payment.setSirenOrderId(this.getId());
        payment.setStatus("Payment Request");
        SirenOrderApplication.applicationContext.getBean(stardoctor.external.PaymentService.class)
                .pay(payment);

    }

    @PreRemove
    public void onPreRemove(){
        OrderCanceled orderCanceled = new OrderCanceled();
        BeanUtils.copyProperties(this, orderCanceled);
        orderCanceled.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        stardoctor.external.Payment payment = new stardoctor.external.Payment();
        // mappings goes here
        payment.setSirenOrderId(this.getId());
        payment.setStatus("Payment Cancel Request");
        SirenOrderApplication.applicationContext.getBean(stardoctor.external.PaymentService.class)
            .refund(payment);


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getProductNm() {
        return productNm;
    }

    public void setProductNm(String productNm) {
        this.productNm = productNm;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
