package hibuy.server.controller;

import hibuy.server.common.response.BaseResponse;
import hibuy.server.dto.userProduct.DeleteUserProductResponse;
import hibuy.server.dto.userProduct.GetHomeUserProductsResponse;
import hibuy.server.dto.userProduct.GetUserProductResponse;
import hibuy.server.dto.userProduct.PostUserProductRequest;
import hibuy.server.dto.userProduct.PostUserProductResponse;
import hibuy.server.dto.userProduct.PutUserProductRequest;
import hibuy.server.dto.userProduct.PutUserProductResponse;
import hibuy.server.service.UserProductService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user-product")
public class UserProductController {

    private final UserProductService userProductService;

    @GetMapping("")
    public BaseResponse<GetHomeUserProductsResponse> getHomeUserProducts(@RequestParam LocalDate date,
            @RequestParam Long userId) {
        log.debug("[UserProductController.getHomeUserProducts]");

        return new BaseResponse<>(userProductService.getHomeUserProducts(date, userId));

    }

    @GetMapping("/edit/{userId}")
    public BaseResponse<List<GetUserProductResponse>> getUserProducts(@PathVariable Long userId) {
        log.debug("[UserProductController.getUserProducts]");

        return new BaseResponse<>(userProductService.getUserProducts(userId));
    }



    @GetMapping("/{userProductId}")
    public BaseResponse<PutUserProductRequest> getUserProduct(@PathVariable Long userProductId) {
        log.debug("[UserProductController.getUserProduct]");

        return new BaseResponse<>(userProductService.getUserProduct(userProductId));
    }

    @PostMapping("")
    public BaseResponse<PostUserProductResponse> createUserProduct(
            @Valid @RequestBody PostUserProductRequest postUserProductRequest) {
        log.debug("[UserProductController.createUserProduct]");

        return new BaseResponse<>(userProductService.createUserProduct(postUserProductRequest));
    }

    @PutMapping("")
    public BaseResponse<PutUserProductResponse> updateUserProduct(
            @Valid @RequestBody PutUserProductRequest request) {
        log.debug("[UserProductController.updateUserProduct]");

        return new BaseResponse<>(userProductService.updateUserProduct(request));
    }

    @DeleteMapping("/{userProductId}")
    public BaseResponse<DeleteUserProductResponse> deleteUserProduct(
            @PathVariable Long userProductId) {
        log.debug("[UserProductController.deleteUserProduct]");

        return new BaseResponse<>(userProductService.deleteUserProduct(userProductId));
    }
}
