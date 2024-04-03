package hibuy.server.service;

import static hibuy.server.domain.Status.INACTIVE;
import static org.assertj.core.api.Assertions.assertThat;

import hibuy.server.controller.LoginController;
import hibuy.server.domain.Product;
import hibuy.server.domain.User;
import hibuy.server.dto.userProduct.DailyUserProductDto;
import hibuy.server.dto.userProduct.PostUserProductRequest;
import hibuy.server.dto.userProduct.PostUserProductResponse;
import hibuy.server.dto.userProduct.PutUserProductRequest;
import hibuy.server.repository.BoolTakeRepository;
import hibuy.server.repository.ProductRepository;
import hibuy.server.repository.UserProductRepository;
import hibuy.server.repository.UserRepository;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserProductServiceTest {

    @Autowired UserRepository userRepository;
    @Autowired ProductRepository productRepository;
    @Autowired UserProductService userProductService;
    @Autowired UserProductRepository userProductRepository;
    @Autowired BoolTakeRepository boolTakeRepository;

    @MockBean LoginController loginController;
    @MockBean KakaoService kakaoService;

    private User user;
    private Product product;
    private List<Time> timeList;
    private List<Integer> dayList;
    private PostUserProductRequest request;

    @BeforeEach
    public void setUp() {
        user = new User("bzun", "email_bzun", "1111");
        userRepository.save(user);
        product = Product.builder()
                .companyName("company")
                .productName("product")
                .price(30000)
                .imageUrl("imageUrl")
                .productUrl("productUrl")
                .category("lactofit")
                .oneTakeAmount(2)
                .totalAmount(100)
                .takeCount(0)
                .build();
        productRepository.save(product);

        timeList = new ArrayList<>();
        timeList.add(Time.valueOf("09:30:00"));
        timeList.add(Time.valueOf("18:45:00"));

        dayList = new ArrayList<>();
        dayList.add(2);
        dayList.add(4);

        request = new PostUserProductRequest("product", 2, 100, timeList, dayList, 1,
                user.getUserId(), "종근당", "imageUrl");
    }
    @Test
    public void createUserProduct() {
        //when
        PostUserProductResponse userProduct = userProductService.createUserProduct(request);

        //then
        assertThat(userProductRepository.findById(userProduct.getUserProductId()).orElseThrow()
                .getUser()).isEqualTo(user);
    }

    @Test
    public void findUserProduct() {
        //given
        PostUserProductResponse userProduct = userProductService.createUserProduct(request);

        List<DailyUserProductDto> test = new ArrayList<>();
        test.add(new DailyUserProductDto(userProduct.getUserProductId(), product.getProductName(),
                request.getOneTakeAmount(), Time.valueOf("09:30:00"), INACTIVE));

        //then
        assertThat(userProductService.getHomeUserProducts(LocalDate.of(2024,1,23) ,user.getUserId())
                .getUserProductDtoList().get(0).getProductName())
                .isEqualTo(test.get(0).getProductName());
        assertThat(userProductService.getHomeUserProducts(LocalDate.of(2024,1,23) ,user.getUserId())
                .getUserProductDtoList().get(0).getOneTakeAmount())
                .isEqualTo(test.get(0).getOneTakeAmount());
        assertThat(userProductService.getHomeUserProducts(LocalDate.of(2024,1,23) ,user.getUserId())
                .getUserProductDtoList().get(0).getStatus())
                .isEqualTo(INACTIVE);
    }

    @Test
    public void updateUserProduct() {
        //given
        PostUserProductResponse userProduct = userProductService.createUserProduct(request);

        List<DailyUserProductDto> test = new ArrayList<>();
        test.add(new DailyUserProductDto(userProduct.getUserProductId(), product.getProductName(),
                request.getOneTakeAmount(), Time.valueOf("12:30:00"), INACTIVE));
        test.add(new DailyUserProductDto(userProduct.getUserProductId(), product.getProductName(),
                request.getOneTakeAmount(), Time.valueOf("21:30:00"), INACTIVE));

        //수정 전
        assertThat(userProductService.getHomeUserProducts(LocalDate.of(2024,1,23) ,user.getUserId())
                .getUserProductDtoList().get(0).getOneTakeAmount())
                .isEqualTo(test.get(0).getOneTakeAmount());

        timeList = new ArrayList<>();
        timeList.add(Time.valueOf("12:30:00"));
        timeList.add(Time.valueOf("21:30:00"));

        dayList = new ArrayList<>();
        dayList.add(3);
        dayList.add(5);

        //when
        PutUserProductRequest putUserProductRequest = new PutUserProductRequest(
                userProduct.getUserProductId(), 1, 150, timeList, dayList, 1);
        userProductService.updateUserProduct(putUserProductRequest);

        //then
        assertThat(userProductService.getHomeUserProducts(LocalDate.of(2024,1,31) ,user.getUserId())
                .getUserProductDtoList().get(0).getProductName())
                .isEqualTo(test.get(0).getProductName());
        assertThat(userProductService.getHomeUserProducts(LocalDate.of(2024,2,2) ,user.getUserId())
                .getUserProductDtoList().get(0).getOneTakeAmount())
                .isEqualTo(1);
        assertThat(userProductService.getHomeUserProducts(LocalDate.of(2024,1,31) ,user.getUserId())
                .getUserProductDtoList().get(0).getStatus())
                .isEqualTo(INACTIVE);
    }
}