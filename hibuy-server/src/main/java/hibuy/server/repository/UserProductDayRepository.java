package hibuy.server.repository;

import hibuy.server.domain.UserProductDay;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserProductDayRepository extends JpaRepository<UserProductDay, Long> {

    @Query("select upd.takeDay from UserProductDay upd where upd.userProduct.id=:userProductId")
    List<Integer> findByUpId(@Param("userProductId") Long userProductId);

}
