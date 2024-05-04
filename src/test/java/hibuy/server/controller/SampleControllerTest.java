package hibuy.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hibuy.server.dto.sample.GetSampleResponse;
import hibuy.server.dto.sample.PostSampleRequest;
import hibuy.server.service.SampleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SampleController.class)
class SampleControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean SampleService sampleService;

    @DisplayName("샘플을 등록합니다.")
    @Test
    void addSample() throws Exception {
        //given
        PostSampleRequest request = new PostSampleRequest("락토핏", "종근당", "http://www.aaa.com", "http://www.bbb.com");

        //when //then
        mockMvc.perform(
                post("/samples")
                            .content(objectMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1000))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("요청에 성공하였습니다."));
    }

    @DisplayName("잘못된 이미지 URL 입력")
    @Test
    void addSampleWithWrongImageUrl() throws Exception {
        //given
        PostSampleRequest request = new PostSampleRequest("락토핏", "종근당", "aaaaa", "http://www.bbb.com");

        //when //then
        mockMvc.perform(
                        post("/samples")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(2000))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("유효하지 않은 입력입니다."))
                .andExpect(jsonPath("$.exceptions[0].field").value("imageUrl"))
                .andExpect(jsonPath("$.exceptions[0].reason").value("must be a valid URL"));
    }

    @DisplayName("잘못된 상품 URL 입력")
    @Test
    void addSampleWithWrongProductUrl() throws Exception {
        //given
        PostSampleRequest request = new PostSampleRequest("락토핏", "종근당", "http://www.aaa.com", "bbbbb");

        //when //then
        mockMvc.perform(
                        post("/samples")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(2000))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("유효하지 않은 입력입니다."))
                .andExpect(jsonPath("$.exceptions[0].field").value("productUrl"))
                .andExpect(jsonPath("$.exceptions[0].reason").value("must be a valid URL"));
    }

    @DisplayName("샘플 리스트를 조회한다.")
    @Test
    void getSamples() throws Exception {
        //given
        List<GetSampleResponse> responses = List.of(
                new GetSampleResponse("락토핏", "종근당", "http://www.aaa.com", "http://www.bbb.com")
        );
        given(sampleService.getSamples()).willReturn(responses);

        //when //then
        mockMvc.perform(get("/samples"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1000))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("요청에 성공하였습니다."))
                .andExpect(jsonPath("$.result[0].productName").value("락토핏"))
                .andExpect(jsonPath("$.result[0].companyName").value("종근당"))
                .andExpect(jsonPath("$.result[0].imageUrl").value("http://www.aaa.com"))
                .andExpect(jsonPath("$.result[0].productUrl").value("http://www.bbb.com"));
    }

}