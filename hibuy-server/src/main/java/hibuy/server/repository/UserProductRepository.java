package hibuy.server.repository;

import hibuy.server.domain.UserProduct;
import hibuy.server.dto.userProduct.DailyUserProductDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProductRepository extends JpaRepository<UserProduct, Long> {

}
