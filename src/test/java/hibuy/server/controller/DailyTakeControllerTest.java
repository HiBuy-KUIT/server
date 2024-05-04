package hibuy.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hibuy.server.dto.dailyTake.GetMonthlyTakeResponse;
import hibuy.server.dto.dailyTake.PostDailyTakeRequest;
import hibuy.server.service.DailyTakeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DailyTakeController.class)
class DailyTakeControllerTest {

    @Autowired DailyTakeController dailyTakeController;
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean DailyTakeService dailyTakeService;

    @DisplayName("월별 조회 여부를 확인한다.")
    @Test
    void getMonthlyTake() throws Exception {
        //given
        Long userId = 1L;
        String year = "2024";
        String month = "04";
        GetMonthlyTakeResponse response = new GetMonthlyTakeResponse(
                List.of(Date.valueOf("2024-04-01"), Date.valueOf("2024-04-02"))
        );
        given(dailyTakeService.getMonthlyTake(userId, year, month)).willReturn(response);


        //when //then
        mockMvc.perform(
                get("/users/{userId}/daily-take", userId)
                        .param("year", year)
                        .param("month", month)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1000))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("요청에 성공하였습니다."))
                .andExpect(jsonPath("$.result.intakeDate[0]").value("2024-04-01"))
                .andExpect(jsonPath("$.result.intakeDate[1]").value("2024-04-02"));
    }

    @DisplayName("일일 섭취여부를 추가한다.")
    @Test
    void addDailyTake() throws Exception {
        //given
        Long userId = 1L;
        PostDailyTakeRequest request = new PostDailyTakeRequest(Date.valueOf("2024-04-05"));

        //when //then
        mockMvc.perform(
                post("/users/{userId}/daily-take", userId)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1000))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("요청에 성공하였습니다."));
    }
}