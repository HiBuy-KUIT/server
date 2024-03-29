package hibuy.server.repository;

import hibuy.server.domain.UserProductDay;
import hibuy.server.domain.UserProductTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserProductDayRepository extends JpaRepository<UserProductDay, Long> {

    @Query("select upd.takeDay from UserProductDay upd where upd.userProduct.id=:userProductId")
    List<Integer> findByUpId(@Param("userProductId") Long userProductId);

    @Modifying
    @Query("delete from UserProductDay upd where upd.userProduct.id = :userProductId")
    void deleteByUserProductId(@Param("userProductId") Long userProductId);

    @Query("select upd from UserProductDay upd where upd.userProduct.id in :userProductIds")
    List<UserProductDay> findByUserProductIds(@Param("userProductIds") List<Long> userProductIds);

}
