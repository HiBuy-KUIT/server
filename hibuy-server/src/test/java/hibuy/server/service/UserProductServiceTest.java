package hibuy.server.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hibuy.server.domain.Product;
import hibuy.server.domain.User;
import hibuy.server.domain.UserProduct;
import hibuy.server.dto.userProduct.PostUserProductRequest;
import hibuy.server.dto.userProduct.PostUserProductResponse;
import hibuy.server.repository.ProductRepository;
import hibuy.server.repository.UserProductRepository;
import hibuy.server.repository.UserRepository;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserProductServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserProductService userProductService;

    @Autowired
    UserProductRepository userProductRepository;

    @Test
    public void createUserProduct() throws Exception {
        //given
        User user = new User("bzun", "eeee", "1111");
        userRepository.save(user);
        Product product = new Product("company", "product", 100, "url", "url2", "cat");
        productRepository.save(product);

        List<Time> timeList = new ArrayList<>();
        timeList.add(Time.valueOf("09:30:00"));
        timeList.add(Time.valueOf("18:45:00"));
        PostUserProductRequest request = new PostUserProductRequest(2, 100, timeList, 1111111, 1,
                user.getUserId(), product.getProductId());


        //when
        PostUserProductResponse userProduct = userProductService.createUserProduct(request);

        //then
        assertThat(userProductRepository.findById(userProduct.getUserProductId()).get()
                .getUser()).isEqualTo(user);
    }
}