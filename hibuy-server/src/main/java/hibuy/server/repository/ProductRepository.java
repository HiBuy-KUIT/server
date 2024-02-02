package hibuy.server.repository;

import hibuy.server.domain.Product;
import hibuy.server.dto.product.GetProductListResponse;
import hibuy.server.dto.product.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new hibuy.server.dto.product.ProductDTO(p.productId, p.productName, p.companyName, p.price, p.imageUrl, p.productUrl)" +
            " FROM Product p" +
            " WHERE p.category =:category AND p.status = 'ACTIVE'")
    List<ProductDTO> findProductsByCategory(@Param("category") String category);

    @Query("SELECT new hibuy.server.dto.product.ProductDTO(p.productId, p.productName, p.companyName, p.price, p.imageUrl, p.productUrl)" +
            " FROM Product p" +
            " WHERE p.productName LIKE %:name% OR p.companyName LIKE %:name%")
    List<ProductDTO> findProductsByName(String name);
}
