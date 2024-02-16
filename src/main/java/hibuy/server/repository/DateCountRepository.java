package hibuy.server.repository;

import hibuy.server.domain.DateCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DateCountRepository extends JpaRepository<DateCount, Long> {

    @Query("SELECT dc FROM DateCount dc JOIN dc.user u WHERE u.userId = :user_id AND u.status = 'ACTIVE'")
    Optional<DateCount> findDateCountByUserId(@Param("user_id") Long userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE DateCount dc set dc.dateCount = dc.dateCount+1 WHERE dc.user.userId = :user_id AND dc.user.status = 'ACTIVE'")
    int updateDateCountByUserId(@Param("user_id") Long userId);
}
