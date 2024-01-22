package hibuy.server.controller;

import hibuy.server.common.response.BaseResponse;
import hibuy.server.dto.userProduct.GetUserProductRequest;
import hibuy.server.dto.userProduct.GetUserProductResponse;
import hibuy.server.dto.userProduct.PostUserProductRequest;
import hibuy.server.dto.userProduct.PostUserProductResponse;
import hibuy.server.dto.userProduct.PutUserProductResponse;
import hibuy.server.service.UserProductService;
import java.sql.Timestamp;
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
    public BaseResponse<GetUserProductResponse> getUserProduct(@RequestParam Timestamp date,
            @RequestParam Long userId) {
        log.debug("[UserProductController.getUserProduct]");

        return new BaseResponse<>(userProductService.getUserProduct(new
                GetUserProductRequest(date, userId)));

    }

    @PostMapping("")
    public BaseResponse<PostUserProductResponse> createUserProduct(
            @RequestBody PostUserProductRequest postUserProductRequest) {
        log.debug("[UserProductController.createUserProduct]");

        return new BaseResponse<>(userProductService.createUserProduct(postUserProductRequest));
    }

//    @PutMapping("")
//    public BaseResponse<PutUserProductResponse> updateUserProduct(
//            @PathVariable Long userProductId) {
//        log.debug("[UserProductController.updateUserProduct]");
//
//
//    }

//    @DeleteMapping("")
//    public BaseResponse<DeleteUserProductResponse> deleteUserProduct(
//            @PathVariable Long userProductId) {
//        log.debug("[UserProductController.deleteUserProduct]");
//    }
}
