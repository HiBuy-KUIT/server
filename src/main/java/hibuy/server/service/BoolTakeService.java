package hibuy.server.service;

import hibuy.server.common.exception.notfound.NotFoundBoolTakeException;
import hibuy.server.domain.BoolTake;
import hibuy.server.dto.booltake.PatchBoolTakeRequest;
import hibuy.server.dto.booltake.PatchBoolTakeResponse;
import hibuy.server.repository.BoolTakeRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoolTakeService {

    private final BoolTakeRepository boolTakeRepository;

    @Transactional
    public PatchBoolTakeResponse updateBoolTake(PatchBoolTakeRequest request) {
        log.debug("[BoolTakeService.updateBoolTake]");

        LocalDateTime localDateTime = request.getLocalDateTimeFromRequest();

        BoolTake boolTake = boolTakeRepository.findByUserProductAndTakeDateTime(
                request.getUserProductId(), Timestamp.valueOf(localDateTime))
                .orElseThrow(NotFoundBoolTakeException::new);

        boolTake.updateBoolTake(request.getStatus());

        return PatchBoolTakeResponse.of(boolTake, localDateTime);
    }

}
