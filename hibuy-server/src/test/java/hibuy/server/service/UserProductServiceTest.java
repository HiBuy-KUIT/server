package hibuy.server.service;

import static org.assertj.core.api.Assertions.assertThat;

import hibuy.server.domain.Product;
import hibuy.server.domain.User;
import hibuy.server.dto.userProduct.DailyUserProductDto;
import hibuy.server.dto.userProduct.GetHomeUserProductsRequest;
import hibuy.server.dto.userProduct.PostUserProductRequest;
import hibuy.server.dto.userProduct.PostUserProductResponse;
import hibuy.server.dto.userProduct.TakeStatusDto;
import hibuy.server.repository.BoolTakeRepository;
import hibuy.server.repository.ProductRepository;
import hibuy.server.repository.UserProductJpaRepository;
import hibuy.server.repository.UserProductRepository;
import hibuy.server.repository.UserRepository;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserProductServiceTest {

    @Autowired UserRepository userRepository;
    @Autowired ProductRepository productRepository;
    @Autowired UserProductService userProductService;
    @Autowired UserProductRepository userProductRepository;
    @Autowired UserProductJpaRepository userProductJpaRepository;
    @Autowired BoolTakeRepository boolTakeRepository;

    @Test
    public void createUserProduct() {
        //given
        User user = new User("bzun", "email_bzun", "1111");
        userRepository.save(user);

        User userX = new User("bzun2", "email_bzun", "1111");
        userRepository.save(user);

        Product product = new Product("company", "product", 30000, "imageUrl", "productUrl", "lactofit");
        productRepository.save(product);

        List<Time> timeList = new ArrayList<>();
        timeList.add(Time.valueOf("09:30:00"));
        timeList.add(Time.valueOf("18:45:00"));
        List<Integer> dayList = new ArrayList<>();
        dayList.add(2);
        dayList.add(4);
        PostUserProductRequest request = new PostUserProductRequest(2, 100, timeList, dayList, 1,
                user.getUserId(), product.getProductId());

        //when
        PostUserProductResponse userProduct = userProductService.createUserProduct(request);

        //then
        assertThat(userProductRepository.findById(userProduct.getUserProductId()).get()
                .getUser()).isEqualTo(user);
    }

    @Test
    public void findUserProduct() {
        //given
        User user = new User("bzun", "email_bzun", "1111");
        User savedUser = userRepository.save(user);
        Product product = new Product("company", "product", 30000, "imageUrl", "productUrl", "");
        Product savedProduct = productRepository.save(product);

        List<Time> timeList = new ArrayList<>();
        timeList.add(Time.valueOf("09:30:00"));
        timeList.add(Time.valueOf("18:45:00"));
        List<Integer> dayList = new ArrayList<>();
        dayList.add(2);
        dayList.add(4);
        PostUserProductRequest request = new PostUserProductRequest(2, 100, timeList, dayList, 1,
                savedUser.getUserId(), savedProduct.getProductId());

        PostUserProductResponse userProduct = userProductService.createUserProduct(request);

        List<DailyUserProductDto> test = new ArrayList<>();
        test.add(new DailyUserProductDto(userProduct.getUserProductId(), product.getProductName(), request.getOneTakeAmount()));
        test.get(0).getTakeStatusDtoList().add(new TakeStatusDto(Time.valueOf("09:30:00"), "INACTIVE"));

        //then
        assertThat(userProductService.getHomeUserProducts(new GetHomeUserProductsRequest(Timestamp.valueOf("2024-01-23 09:30:00"),user.getUserId()))
                .getUserProductDtoList().get(0).getProductName())
                .isEqualTo(test.get(0).getProductName());
        assertThat(userProductService.getHomeUserProducts(new GetHomeUserProductsRequest(Timestamp.valueOf("2024-01-23 09:30:00"),user.getUserId()))
                .getUserProductDtoList().get(0).getProductName())
                .isEqualTo(test.get(0).getProductName());
        assertThat(userProductService.getHomeUserProducts(new GetHomeUserProductsRequest(Timestamp.valueOf("2024-01-23 09:30:00"),user.getUserId()))
                .getUserProductDtoList().get(0).getProductName())
                .isEqualTo(test.get(0).getProductName());
        assertThat(userProductService.getHomeUserProducts(new GetHomeUserProductsRequest(Timestamp.valueOf("2024-01-23 09:30:00"),user.getUserId()))
                .getUserProductDtoList().get(0).getTakeStatusDtoList().get(0).getStatus())
                .isEqualTo("INACTIVE");
    }
}