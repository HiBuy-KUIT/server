package hibuy.server.service;

import hibuy.server.domain.Product;
import hibuy.server.domain.User;
import hibuy.server.domain.UserProduct;
import hibuy.server.domain.UserProductTime;
import hibuy.server.dto.userProduct.GetUserProductRequest;
import hibuy.server.dto.userProduct.GetUserProductResponse;
import hibuy.server.dto.userProduct.PostUserProductRequest;
import hibuy.server.dto.userProduct.PostUserProductResponse;
import hibuy.server.repository.ProductRepository;
import hibuy.server.repository.UserProductRepository;
import hibuy.server.repository.UserProductTimeRepository;
import hibuy.server.repository.UserRepository;
import java.sql.Time;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserProductRepository userProductRepository;
    private final UserProductTimeRepository userProductTimeRepository;

//    public GetUserProductResponse getUserProduct(GetUserProductRequest getUserProductRequest) {
//        log.debug("[UserProductService.getUserProduct]");
//
//
//    }


    public PostUserProductResponse createUserProduct(PostUserProductRequest request) {
        log.debug("[UserProductService.createUserProduct]");

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + request.getProductId()));
        UserProduct userProduct = new UserProduct(
                request.getOneTakeAmount(),
                request.getTotalAmount(),
                request.getTakeDay(),
                request.getNotification(),
                user,
                product
        );
        userProductRepository.save(userProduct);

        for (Time time : request.getTakeTimeList()) {
            userProductTimeRepository.save(new UserProductTime(time, userProduct));
        }

        return new PostUserProductResponse(userProduct.getId());
    }

//    private void validateUser(Long userId) {
//        if (userRepository.findById(userId) == null) {
//
//        }
//    }
}
