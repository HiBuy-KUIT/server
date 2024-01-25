package hibuy.server.repository;

import hibuy.server.domain.UserProductTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserProductTimeRepository extends JpaRepository<UserProductTime, Long> {

    @Query("select upt from UserProductTime upt where upt.userProduct.id in :userProductIds")
    List<UserProductTime> findByUserProductId(@Param("userProductIds") List<Long> userProductIds);

    @Query("delete from UserProductTime upt where upt.userProduct.id = :userProductId")
    void deleteByUserProductId(Long userProductId);
}
