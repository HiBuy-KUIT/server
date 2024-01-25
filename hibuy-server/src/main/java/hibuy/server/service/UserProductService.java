package hibuy.server.service;

import hibuy.server.domain.BoolTake;
import hibuy.server.domain.Product;
import hibuy.server.domain.User;
import hibuy.server.domain.UserProduct;
import hibuy.server.domain.UserProductDay;
import hibuy.server.domain.UserProductTime;
import hibuy.server.dto.userProduct.DailyUserProductDto;
import hibuy.server.dto.userProduct.DeleteUserProductResponse;
import hibuy.server.dto.userProduct.GetHomeUserProductsRequest;
import hibuy.server.dto.userProduct.GetHomeUserProductsResponse;
import hibuy.server.dto.userProduct.PostUserProductRequest;
import hibuy.server.dto.userProduct.PostUserProductResponse;
import hibuy.server.dto.userProduct.PutUserProductRequest;
import hibuy.server.dto.userProduct.TakeStatusDto;
import hibuy.server.repository.BoolTakeRepository;
import hibuy.server.repository.ProductRepository;
import hibuy.server.repository.UserProductDayRepository;
import hibuy.server.repository.UserProductJpaRepository;
import hibuy.server.repository.UserProductRepository;
import hibuy.server.repository.UserProductTimeRepository;
import hibuy.server.repository.UserRepository;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
    private final UserProductDayRepository userProductDayRepository;
    private final UserProductJpaRepository userProductJpaRepository;
    private final BoolTakeRepository boolTakeRepository;

    public GetHomeUserProductsResponse getHomeUserProducts(GetHomeUserProductsRequest request) {
        log.debug("[UserProductService.getUserProduct]");

        int day = request.getTakeTimestamp().toLocalDateTime().getDayOfWeek().getValue();
        LocalDate localDate = request.getTakeTimestamp().toLocalDateTime().toLocalDate();

        //오늘 먹을 영양제
        List<DailyUserProductDto> todayUserProducts = userProductJpaRepository.findByUserAndDate(request.getUserId(), day);

        //의 UserProduct id List
        List<Long> userProductIds = todayUserProducts.stream()
                .map(DailyUserProductDto::getUserProductId)
                .toList();

        //들의 섭취 시간 List, Map
        List<UserProductTime> userProductTimeList = userProductTimeRepository.findByUserProductIds(userProductIds);
        Map<Long, List<UserProductTime>> userProductTimeMap = userProductTimeList.stream()
                .collect(Collectors.groupingBy(userProductTime -> userProductTime.getUserProduct().getId()));

        //들의 섭취 여부 List, Map
        List<BoolTake> boolTakeList = boolTakeRepository.findByUserProductIds(userProductIds);
        Map<Long, List<BoolTake>> boolTakeMap = boolTakeList.stream()
                .collect(Collectors.groupingBy(boolTake -> boolTake.getUserProduct().getId()));

        //각 UserProduct
        for (DailyUserProductDto dailyUserProductDto : todayUserProducts) {
            //의 각 섭취시간별 섭취여부 판별
            for (UserProductTime userProductTime : userProductTimeMap.get(dailyUserProductDto.getUserProductId())) {
                //날짜와 시간 결합
                Timestamp takeTime = Timestamp.valueOf(LocalDateTime.of(localDate,
                        userProductTime.getTakeTime().toLocalTime()));
                String status = "INACTIVE";

                try {
                    for (BoolTake boolTake : boolTakeMap.get(
                            dailyUserProductDto.getUserProductId())) {
                        if (boolTake.getTakeDateTime().equals(takeTime)) {
                            status = "ACTIVE";
                            break;
                        }
                    }
                } catch (NullPointerException e) {}

                if (status.equals("ACTIVE")) {
                    dailyUserProductDto.getTakeStatusDtoList().add(new TakeStatusDto(userProductTime.getTakeTime(), "ACTIVE"));
                } else {
                    dailyUserProductDto.getTakeStatusDtoList().add(new TakeStatusDto(userProductTime.getTakeTime(), "INACTIVE"));
                }
            }
        }

        return new GetHomeUserProductsResponse(todayUserProducts);
    }

    public PutUserProductRequest getUserProduct(Long userProductId) {
        log.debug("[UserProductService.getUserProduct]");

        UserProduct userProduct = userProductRepository.findById(userProductId).orElseThrow();
        List<Integer> days = userProductDayRepository.findByUpId(userProductId);
        List<Time> times = userProductTimeRepository.findByUpId(userProductId);

        return new PutUserProductRequest(
                userProductId, userProduct.getOneTakeAmount(), userProduct.getTotalAmount(), times,
                days,
                userProduct.getNotification(), userProduct.getUser().getUserId(),
                userProduct.getProduct().getProductId()
        );
    }

    public PostUserProductResponse createUserProduct(PostUserProductRequest request) {
        log.debug("[UserProductService.createUserProduct]");

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + request.getProductId()));
        UserProduct userProduct = new UserProduct(
                request.getOneTakeAmount(),
                request.getTotalAmount(),
                request.getNotification(),
                user,
                product
        );
        userProductRepository.save(userProduct);

        for (Time time : request.getTakeTimeList()) {
            userProductTimeRepository.save(new UserProductTime(time, userProduct));
        }
        for (Integer day : request.getTakeDay()) {
            userProductDayRepository.save(new UserProductDay(day, userProduct));
        }

        return new PostUserProductResponse(userProduct.getId());
    }

    public DeleteUserProductResponse deleteUserProduct(Long userProductId) {
        log.debug("[UserProductService.deleteUserProduct]");

        boolTakeRepository.deleteByUserProductId(userProductId);
        userProductTimeRepository.deleteByUserProductId(userProductId);
        userProductDayRepository.deleteByUserProductId(userProductId);
        userProductRepository.deleteById(userProductId);

        return new DeleteUserProductResponse(userProductId);
    }
}
