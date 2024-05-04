package hibuy.server.service;

import hibuy.server.common.exception.notfound.NotFoundUserException;
import hibuy.server.domain.DailyTake;
import hibuy.server.domain.User;
import hibuy.server.dto.dailyTake.GetMonthlyTakeResponse;
import hibuy.server.repository.DailyTakeRepository;
import hibuy.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
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

        List<Date> takeDates = dailyTakeRepository.findTakeDatesByUserId(userId);
        
        return new GetMonthlyTakeResponse(filterAndSortByYearAndMonth(year, month, takeDates));

    }

    @Transactional
    public void addDailyTake(Long userId, Date date) {

        log.debug("[DailyTakeService.addDailyTake]");

        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);

        Optional<DailyTake> dailyTakeOptional = dailyTakeRepository.findDailyTakeByUserIdAAndTakeDate(userId, date);

        if(dailyTakeOptional.isEmpty()) {
            dailyTakeRepository.save(new DailyTake(date, user));
            return;
        }

        DailyTake dailyTake = dailyTakeOptional.get();

        if(dailyTake.isStatusInactive()) {
            dailyTake.changeStatusToActive();
        }

    }

    private List<Date> filterAndSortByYearAndMonth(String year, String month, List<Date> takeDates) {
        return takeDates.stream()
                .filter(takeDate -> takeDate.toString().split(DATE_DELIMITER)[0].equals(year))
                .filter(takeDate -> takeDate.toString().split(DATE_DELIMITER)[1].equals(month))
                .sorted()
                .toList();
    }
}
