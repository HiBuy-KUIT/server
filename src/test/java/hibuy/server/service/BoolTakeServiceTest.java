package hibuy.server.service;

import static hibuy.server.domain.Status.ACTIVE;
import static hibuy.server.domain.Status.INACTIVE;
import static org.assertj.core.api.Assertions.assertThat;

import hibuy.server.controller.LoginController;
import hibuy.server.domain.Product;
import hibuy.server.domain.User;
import hibuy.server.dto.booltake.PatchBoolTakeRequest;
import hibuy.server.dto.booltake.PatchBoolTakeResponse;
import hibuy.server.dto.userProduct.PostUserProductRequest;
import hibuy.server.dto.userProduct.PostUserProductResponse;
import hibuy.server.repository.BoolTakeRepository;
import hibuy.server.repository.ProductRepository;
import hibuy.server.repository.UserProductRepository;
import hibuy.server.repository.UserRepository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
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
class BoolTakeServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserProductService userProductService;
    @Autowired
    UserProductRepository userProductRepository;
    @Autowired
    BoolTakeService boolTakeService;
    @Autowired
    BoolTakeRepository boolTakeRepository;

    @MockBean
    LoginController loginController;
    @MockBean
    KakaoService kakaoService;

    private User user;
    private Product product;
    private List<Time> timeList;
    private List<Integer> dayList;
    private PostUserProductRequest request;

    @BeforeEach
    public void setUp() {
        user = new User(123L, "bzun", "email_bzun", "1111");
        userRepository.save(user);

        Product product = Product.builder()
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
    public void updateBoolTake() {
        //given
        PostUserProductResponse userProduct = userProductService.createUserProduct(request);

        PatchBoolTakeRequest patchBoolTakeRequest = new PatchBoolTakeRequest(
                userProduct.getUserProductId(), Date.valueOf("2024-01-30"),
                Time.valueOf("09:30:00"), ACTIVE);

        //when
        userProductService.getHomeUserProducts(LocalDate.of(2024, 1, 30), user.getUserId());

        assertThat(boolTakeRepository.findByUserProductAndTakeDateTime(
                userProduct.getUserProductId(), Timestamp.valueOf("2024-01-30 09:30:00")).get().getStatus()).isEqualTo(INACTIVE);

        PatchBoolTakeResponse response = boolTakeService.updateBoolTake(
                patchBoolTakeRequest);

        //then
        assertThat(boolTakeRepository.findByUserProductAndTakeDateTime(response.getUserProductId(),
                Timestamp.valueOf(response.getTakeDateTime())).get().getStatus()).isEqualTo(ACTIVE);
    }
}