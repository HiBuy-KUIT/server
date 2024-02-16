package hibuy.server.repository;

import hibuy.server.domain.UserProduct;
import hibuy.server.dto.userProduct.GetUserProductResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserProductRepository extends JpaRepository<UserProduct, Long> {

    @Query("select new hibuy.server.dto.userProduct.GetUserProductResponse(up.id, up.productName, up.imageUrl, up.oneTakeAmount)"
            + " from UserProduct up"
            + " where up.user.userId=:userId")
    List<GetUserProductResponse> findUserProductByUserId(@Param("userId") Long userId);
}