package hibuy.server.repository;

import hibuy.server.dto.userProduct.DailyUserProductDto;
import hibuy.server.dto.userProduct.TakeStatusDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserProductJpaRepository {

    @PersistenceContext
    private final EntityManager em;

    public List<DailyUserProductDto> findByUserAndDate(Long userId, Timestamp takeDay) {

        int day = takeDay.toLocalDateTime().getDayOfWeek().getValue();

        LocalDate localDate = takeDay.toLocalDateTime().toLocalDate();

        List<DailyUserProductDto> todayUserProduct = em.createQuery(
                "select new hibuy.server.dto.userProduct.DailyUserProductDto("
                        + " up.id, p.productName, up.oneTakeAmount)"
                        + " from UserProduct up"
                        + " join BoolTake bt on up.id=bt.userProduct.id"
                        + " join fetch Product p"
                        + " join UserProductDay upd on upd.userProduct.id=up.id and upd.day=:day"
                        + " where up.user.userId=:userId and bt.takeDate=:takeDay",
                DailyUserProductDto.class)
                .setParameter("day", day)
                .setParameter("userId", userId)
                .setParameter("takeDay", takeDay)
                .getResultList();

        for (DailyUserProductDto dailyUserProductDto : todayUserProduct) {
            List<Time> result = em.createQuery("select upt.takeTime"
                            + " from UserProductTime upt"
                            + " join fetch UserProduct up"
                            + " where upt.userProduct.id=up.id", Time.class).getResultList();

            for (Time time : result) {
                LocalDateTime localDateTime = LocalDateTime.of(localDate, time.toLocalTime());
                Timestamp timestamp = Timestamp.valueOf(localDateTime);

                String status = em.createQuery("select bt.status from BoolTake bt"
                                + " where bt.takeDate=:timestamp and bt.userProduct.id=:userProductId")
                        .setParameter("timestamp", timestamp)
                        .setParameter("userProductId", dailyUserProductDto.getUserProductId())
                        .getSingleResult().toString();

                if (status.equals("ACTIVE")) {
                    dailyUserProductDto.getTakeStatusDtoList().add(new TakeStatusDto(time, "ACTIVE"));
                } else {
                    dailyUserProductDto.getTakeStatusDtoList().add(new TakeStatusDto(time, "INACTIVE"));
                }
            }
        }
        return todayUserProduct;
    }
}