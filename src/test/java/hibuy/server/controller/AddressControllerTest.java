package hibuy.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hibuy.server.domain.Address;
import hibuy.server.domain.User;
import hibuy.server.dto.address.*;
import hibuy.server.service.AddressService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AddressController.class)
class AddressControllerTest {

    @Autowired
    AddressController addressController;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    AddressService addressService;

    @DisplayName("주소를 등록한다.")
    @Test
    void addAddress() throws Exception {
        //given
        Long userId = 1L;
        PostAddressRequest request =
                PostAddressRequest.builder()
                        .addressName("학교")
                        .receiver("김건국")
                        .phoneNumber("01012341234")
                        .zipCode("08521")
                        .basicAddress("서울시 광진구")
                        .detailAddress("신공학관 12층")
                        .defaultAddress(false)
                        .request("요청사항 없음")
                        .userId(userId)
                        .build();

        //when //then
        mockMvc.perform(
                        post("/address")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1000))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("요청에 성공하였습니다."));
    }

    @DisplayName("주소를 조회 결과를 가져온다.")
    @Test
    void getAddressList() throws Exception {
        //given
        Long userId = 1L;
        User user = User.builder()
                .kakaoUserId(1L)
                .email("abcd123@naver.com")
                .name("김철수")
                .phoneNumber("01012341234")
                .build();

        GetAddressListResponse response = new GetAddressListResponse(
                List.of(
                        new AddressDTO(Address.builder()
                                .addressName("학교")
                                .receiver("김철수")
                                .phoneNumber("01012341234")
                                .zipCode("08521")
                                .basicAddress("서울시 광진구")
                                .detailAddress("신공학관 12층")
                                .defaultAddress(false)
                                .request("요청사항 없음")
                                .user(user)
                                .build()
                        )
                )
        );

        given(addressService.getAddressListById(userId)).willReturn(response);

        //when //then
        mockMvc.perform(
                        get("/address")
                                .param("userId", userId.toString())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1000))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("요청에 성공하였습니다."))
                .andExpect(jsonPath("$.result.addresses[0].addressName").value("학교"))
                .andExpect(jsonPath("$.result.addresses[0].receiver").value("김철수"))
                .andExpect(jsonPath("$.result.addresses[0].phoneNumber").value("01012341234"))
                .andExpect(jsonPath("$.result.addresses[0].basicAddress").value("서울시 광진구"))
                .andExpect(jsonPath("$.result.addresses[0].detailAddress").value("신공학관 12층"))
                .andExpect(jsonPath("$.result.addresses[0].request").value("요청사항 없음"))
                .andExpect(jsonPath("$.result.addresses[0].defaultAddress").value(false));
    }

    @DisplayName("기본 주소로 등록한다.")
    @Test
    void updateDefaultAddress() throws Exception {
        //given
        Long addressId = 1L;
        Long userId = 1L;
        PatchAddressResponse response = new PatchAddressResponse(addressId);
        PatchAddressRequest request = new PatchAddressRequest(addressId, userId, "요청사항 없음");
        given(addressService.updateDefaultAddress(request)).willReturn(response);

        //when //then
        mockMvc.perform(
                        patch("/address")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1000))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("요청에 성공하였습니다."));
    }
}