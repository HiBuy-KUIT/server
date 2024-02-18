package hibuy.server.repository;

import hibuy.server.domain.UserProduct;
import hibuy.server.dto.userProduct.GetUserProductResponse;
import hibuy.server.dto.userProduct.UserProductDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserProductRepository extends JpaRepository<UserProduct, Long> {

    @Query("select new hibuy.server.dto.userProduct.GetUserProductResponse(up.id, up.productName, up.imageUrl, up.oneTakeAmount)"
            + " from UserProduct up"
            + " where up.user.userId=:userId")
    List<GetUserProductResponse> findUserProductByUserId(@Param("userId") Long userId);

    @Query("select new hibuy.server.dto.userProduct.UserProductDto(up.id, up.productName, up.oneTakeAmount)"
            + " from UserProduct up"
            + " join UserProductDay upd on upd.userProduct.id=up.id and upd.takeDay=:day"
            + " where up.user.userId=:userId")
    List<UserProductDto> findByUserAndDate(@Param("userId") Long userId, @Param("day") int day);
}