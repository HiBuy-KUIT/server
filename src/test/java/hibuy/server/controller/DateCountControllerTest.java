package hibuy.server.controller;

import hibuy.server.domain.DateCount;
import hibuy.server.domain.User;
import hibuy.server.dto.dateCount.GetDateCountResponse;
import hibuy.server.service.DateCountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DateCountController.class)
class DateCountControllerTest {

    @Autowired DateCountController dateCountController;
    @Autowired MockMvc mockMvc;
    @MockBean DateCountService dateCountService;

    @DisplayName("누적 일수를 조회한다.")
    @Test
    void getUserDateCount() throws Exception {
        //given
        Long userId = 1L;
        User user = User.builder()
                .kakaoUserId(1L)
                .email("abcd123@naver.com")
                .name("김철수")
                .phoneNumber("01012341234")
                .build();
        GetDateCountResponse response = GetDateCountResponse.of(new DateCount(user));
        given(dateCountService.getUserDateCount(userId)).willReturn(response);

        //when //then
        mockMvc.perform(
                get("/users/{userId}/count", userId)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1000))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("요청에 성공하였습니다."))
                .andExpect(jsonPath("$.result.dateCount").value(0));
    }

    @DisplayName("누적 일수를 업데이트한다.")
    @Test
    void updateUserDateCount() throws Exception {
        //given
        Long userId = 1L;
        given(dateCountService.updateUserDateCount(userId)).willReturn(1);

        //when //then
        mockMvc.perform(patch("/users/{userId}/count", userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1000))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("요청에 성공하였습니다."));
    }

}