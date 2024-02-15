package hibuy.server.controller;

import hibuy.server.common.response.BaseResponse;

import hibuy.server.dto.product.*;
import hibuy.server.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public BaseResponse<PostProductResponse> addProduct(@Validated @RequestBody PostProductRequest postProductRequest) {
        log.debug("[ProductController.addProduct]");
        return new BaseResponse<>(productService.addProduct(postProductRequest));
    }

    @GetMapping
    public BaseResponse<GetProductListResponse> getProductByCategory(@RequestParam String category){
        log.debug("[ProductController.getProductByCategory]");
        return new BaseResponse<>(productService.getProductListByCategory(category));
    }

    @GetMapping("/search")
    public BaseResponse<GetProductSearchListResponse> getProductByName(@RequestParam String name){
        log.debug("[ProductController.getProductByName]");
        return new BaseResponse<>(productService.getProductListByName(name));
    }

    @GetMapping("/{productId}")
    public BaseResponse<GetProductInfoResponse> getProductInfo(@PathVariable Long productId){
        log.debug("[ProductController.getProductInfo]");
        return new BaseResponse<>(productService.getProductInfo(productId));
    }
}
