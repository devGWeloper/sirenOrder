package stardoctor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="SirenOrderHome_table")
public class SirenOrderHome {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long sirenOrderId;
        private Long paymentId;
        private String productNm;
        private Integer qty;
        private String status;


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
        public Long getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(Long paymentId) {
            this.paymentId = paymentId;
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
