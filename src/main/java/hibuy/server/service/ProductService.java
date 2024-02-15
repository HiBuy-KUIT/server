package hibuy.server.service;

import hibuy.server.domain.Product;
import hibuy.server.dto.product.GetProductListResponse;
import hibuy.server.dto.product.GetProductSearchListResponse;
import hibuy.server.dto.product.PostProductRequest;
import hibuy.server.dto.product.PostProductResponse;
import hibuy.server.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public PostProductResponse addProduct(PostProductRequest postProductRequest) {
        log.debug("[ProductService.addProduct]");

        Product product = productRepository.save(
                new Product(
                        postProductRequest.getCompanyName(),
                        postProductRequest.getProductName(),
                        postProductRequest.getPrice(),
                        postProductRequest.getImageUrl(),
                        postProductRequest.getProductUrl(),
                        postProductRequest.getCategory(),
                        postProductRequest.getOneTakeAmount(),
                        postProductRequest.getTotalAmount(),
                        postProductRequest.getTakeCount()));

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
}
