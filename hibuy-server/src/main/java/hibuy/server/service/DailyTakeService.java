package hibuy.server.service;

import hibuy.server.dto.dailyTake.GetMonthlyTakeResponse;
import hibuy.server.repository.DailyTakeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyTakeService {

    private final DailyTakeRepository dailyTakeRepository;
    private final String DATE_DELIMITER = "-";

    public GetMonthlyTakeResponse getMonthlyTake(Long userId, String year, String month) {

        log.debug("DailyTakeService.getMonthlyTake");

        return new GetMonthlyTakeResponse(dailyTakeRepository.findTakeDatesByUserId(userId).stream()
                .filter(takeDate -> takeDate.toString().split(DATE_DELIMITER)[0].equals(year))
                .filter(takeDate -> takeDate.toString().split(DATE_DELIMITER)[1].equals(month))
                .sorted()
                .toList());

    }
}
