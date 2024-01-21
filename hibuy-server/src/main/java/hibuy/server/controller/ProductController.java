package hibuy.server.controller;

import hibuy.server.common.response.BaseResponse;

import hibuy.server.dto.product.GetProductResponse;
import hibuy.server.dto.product.PostProductRequest;
import hibuy.server.dto.product.PostProductResponse;
import hibuy.server.repository.ProductRepository;
import hibuy.server.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public BaseResponse<PostProductResponse> addProduct(@RequestBody PostProductRequest postProductRequest) {
        log.debug("[ProductController.addProduct]");
        return new BaseResponse<>(productService.addProduct(postProductRequest));
    }

    @GetMapping
    public BaseResponse<GetProductResponse> getProductByCategory(@RequestParam String category){
        log.debug("[ProductController.getProductByCategory]");
        return new BaseResponse<>(productService.getProductListByCategory(category));
    }
}
