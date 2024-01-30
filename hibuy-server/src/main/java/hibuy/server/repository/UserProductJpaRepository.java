package hibuy.server.repository;

import hibuy.server.dto.userProduct.DailyUserProductDto;
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

    public List<DailyUserProductDto> findByUserAndDate(Long userId, int day) {

        return em.createQuery(
                "select new hibuy.server.dto.userProduct.DailyUserProductDto("
                        + " up.id, p.productName, up.oneTakeAmount)"
                        + " from UserProduct up"
                        + " join fetch Product p on up.product.id=p.id"
                        + " join UserProductDay upd on upd.userProduct.id=up.id and upd.takeDay=:day"
                        + " where up.user.userId=:userId",
                DailyUserProductDto.class)
                .setParameter("day", day)
                .setParameter("userId", userId)
                .getResultList();
    }
}