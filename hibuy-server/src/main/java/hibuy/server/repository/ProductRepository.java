package hibuy.server.repository;

import hibuy.server.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p.productId, p.productName, p.companyName, p.price, p.imageUrl, p.productUrl FROM Product p WHERE p.category =:category AND p.status = 'ACTIVE'")
    List<Product> findProductsByCategory(@Param("category") String category);
}