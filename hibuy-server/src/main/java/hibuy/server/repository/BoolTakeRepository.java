package hibuy.server.repository;

import hibuy.server.domain.BoolTake;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoolTakeRepository extends JpaRepository<BoolTake, Long> {

    @Query("select bt from BoolTake bt where bt.userProduct.id in :userProductIds")
    List<BoolTake> findByUserProductIds(@Param("userProductIds") List<Long> userProductIds);

    @Query("delete from BoolTake b where b.userProduct.id = :userProductId")
    void deleteByUserProductId(@Param("userProductId") Long userProductId);

    @Query("select bt from BoolTake bt where bt.userProduct.id=:userProductId and bt.takeDateTime=:takeDateTime")
    BoolTake findByUserProductAndTakeDateTime(@Param("userProductId") Long userProductId,
            @Param("takeDateTime") Timestamp takeDateTime);
}
