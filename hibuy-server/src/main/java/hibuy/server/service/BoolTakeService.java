package hibuy.server.service;

import hibuy.server.domain.BoolTake;
import hibuy.server.dto.booltake.PatchBoolTakeRequest;
import hibuy.server.dto.booltake.PatchBoolTakeResponse;
import hibuy.server.repository.BoolTakeRepository;
import hibuy.server.repository.UserProductRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoolTakeService {

    private final BoolTakeRepository boolTakeRepository;

    public PatchBoolTakeResponse updateBoolTake(PatchBoolTakeRequest request) {
        LocalDateTime localDateTime = LocalDateTime.of(request.getTakeDate().toLocalDate(),
                request.getTakeTime().toLocalTime());
        Timestamp takeDateTime = Timestamp.valueOf(localDateTime);

        BoolTake boolTake = boolTakeRepository.findByUserProductAndTakeDateTime(
                request.getUserProductId(), takeDateTime);

        boolTake.updateBoolTake(request.getStatus());

        return new PatchBoolTakeResponse(boolTake.getUserProduct().getId(), takeDateTime);
    }
}
