package hibuy.server.repository;

import hibuy.server.domain.UserProductTime;
import java.sql.Time;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserProductTimeRepository extends JpaRepository<UserProductTime, Long> {

    @Query("select upt from UserProductTime upt where upt.userProduct.id in :userProductIds")
    List<UserProductTime> findByUserProductIds(@Param("userProductIds") List<Long> userProductIds);

    @Modifying
    @Query("delete from UserProductTime upt where upt.userProduct.id = :userProductId")
    void deleteByUserProductId(@Param("userProductId") Long userProductId);

    @Query("select upt.takeTime from UserProductTime upt where upt.userProduct.id=:userProductId")
    List<Time> findByUpId(@Param("userProductId") Long userProductId);
}
