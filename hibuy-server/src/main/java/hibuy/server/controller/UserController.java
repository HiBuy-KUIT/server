package hibuy.server.controller;

import hibuy.server.common.response.BaseResponse;
import hibuy.server.dto.user.PostUserRequest;
import hibuy.server.dto.user.PostUserResponse;
import hibuy.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public BaseResponse<PostUserResponse> adduser(@RequestBody PostUserRequest postUserRequest) {

        log.debug("[UserController.addUser]");

        return new BaseResponse<>(userService.addUser(postUserRequest));
    }
}
