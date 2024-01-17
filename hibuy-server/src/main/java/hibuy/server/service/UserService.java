package hibuy.server.service;

import hibuy.server.domain.DateCount;
import hibuy.server.domain.User;
import hibuy.server.dto.user.PostUserRequest;
import hibuy.server.dto.user.PostUserResponse;
import hibuy.server.repository.DateCountRepository;
import hibuy.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DateCountRepository dateCountRepository;
    public PostUserResponse addUser(PostUserRequest postUserRequest) {

        log.debug("[UserService.addUser]");

        User user = userRepository.save(
                new User(postUserRequest.getName(), postUserRequest.getEmail(), postUserRequest.getPhoneNumber())
        );

        DateCount dateCount = dateCountRepository.save(new DateCount(user));

        return new PostUserResponse(user.getUserId(), dateCount.getDateCountId());
    }

}
