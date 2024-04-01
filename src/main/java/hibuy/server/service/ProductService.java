package hibuy.server.service;

import hibuy.server.common.exception.notfound.NotFoundProductException;
import hibuy.server.domain.Product;
import hibuy.server.dto.product.*;
import hibuy.server.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public PostProductResponse addProduct(PostProductRequest postProductRequest) {
        log.debug("[ProductService.addProduct]");

        Product product = productRepository.save(postProductRequest.toEntity());

        return new PostProductResponse(product.getProductId());

    }

    public GetProductListResponse getProductListByCategory(String category) {
        log.debug("[ProductService.getProductListByCategory]");
        return new GetProductListResponse(productRepository.findProductsByCategory(category));
    }

    public GetProductSearchListResponse getProductListByName(String name) {
        log.debug("[ProductService.getProductListByName]");
        return new GetProductSearchListResponse(productRepository.findProductsByName(name));
    }

    public GetProductInfoResponse getProductInfo(Long productId) {
        log.debug("[ProductService.getProductInfoResponse]");

        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new);

        return GetProductInfoResponse.from(product);
    }
}
