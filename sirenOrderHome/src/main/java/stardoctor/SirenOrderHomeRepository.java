package stardoctor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SirenOrderHomeRepository extends CrudRepository<SirenOrderHome, Long> {

    List<SirenOrderHome> findBySirenOrderId(Long sirenOrderId);

}