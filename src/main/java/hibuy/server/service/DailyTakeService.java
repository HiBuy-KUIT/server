package hibuy.server.service;

import hibuy.server.domain.DailyTake;
import hibuy.server.domain.User;
import hibuy.server.dto.dailyTake.GetMonthlyTakeResponse;
import hibuy.server.repository.DailyTakeRepository;
import hibuy.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyTakeService {

    private final DailyTakeRepository dailyTakeRepository;
    private final UserRepository userRepository;
    private final String DATE_DELIMITER = "-";

    public GetMonthlyTakeResponse getMonthlyTake(Long userId, String year, String month) {

        log.debug("[DailyTakeService.getMonthlyTake]");

        return new GetMonthlyTakeResponse(dailyTakeRepository.findTakeDatesByUserId(userId).stream()
                .filter(takeDate -> takeDate.toString().split(DATE_DELIMITER)[0].equals(year))
                .filter(takeDate -> takeDate.toString().split(DATE_DELIMITER)[1].equals(month))
                .sorted()
                .toList());

    }

    public void addDailyTake(Long userId, Date date) {

        log.debug("[DailyTakeService.addDailyTake]");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        dailyTakeRepository.save(new DailyTake(date, user));

    }
}
