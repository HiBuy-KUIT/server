package hibuy.server.service;

import hibuy.server.common.exception.DatabaseException;
import hibuy.server.domain.DateCount;
import hibuy.server.dto.dateCount.GetDateCountResponse;
import hibuy.server.repository.DateCountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static hibuy.server.common.response.status.BaseExceptionResponseStatus.DATABASE_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
public class DateCountService {

    private final DateCountRepository dateCountRepository;

    public GetDateCountResponse getUserDateCount(Long userId) {

        log.debug("[DateCountService.getUserDateCount]");

        DateCount dateCount = dateCountRepository.findDateCountByUserId(userId)
                .orElseThrow(() -> new DatabaseException(DATABASE_ERROR));
        return new GetDateCountResponse(dateCount.getUser().getUserId(), dateCount.getDateCount());

    }

    @Transactional
    public void updateUserDateCount(Long userId) {

        log.debug("[PatchDateCountResponse.updateUserDateCount]");

        DateCount dateCount = dateCountRepository.findDateCountByUserId(userId)
                .orElseThrow(() -> new DatabaseException(DATABASE_ERROR));

        dateCount.increaseDateCount();

    }
}
