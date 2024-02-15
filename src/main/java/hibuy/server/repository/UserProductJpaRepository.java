package hibuy.server.repository;

import hibuy.server.dto.userProduct.DailyUserProductDto;
import hibuy.server.dto.userProduct.UserProductDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserProductJpaRepository {

    @PersistenceContext
    private final EntityManager em;

    public List<UserProductDto> findByUserAndDate(Long userId, int day) {

        return em.createQuery(
                "select new hibuy.server.dto.userProduct.UserProductDto("
                        + " up.id, up.productName, up.oneTakeAmount)"
                        + " from UserProduct up"
                        + " join UserProductDay upd on upd.userProduct.id=up.id and upd.takeDay=:day"
                        + " where up.user.userId=:userId",
                UserProductDto.class)
                .setParameter("day", day)
                .setParameter("userId", userId)
                .getResultList();
    }
}