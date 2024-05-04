package hibuy.server.service;

import hibuy.server.controller.LoginController;
import hibuy.server.domain.Address;
import hibuy.server.domain.User;
import hibuy.server.dto.address.PatchAddressRequest;
import hibuy.server.dto.address.PatchAddressResponse;
import hibuy.server.dto.address.PostAddressRequest;
import hibuy.server.dto.address.PostAddressResponse;
import hibuy.server.repository.AddressRepository;
import hibuy.server.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AddressServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressService addressService;

    private User user;
    private Address address;
    private PostAddressRequest request;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .kakaoUserId(1L)
                .name("jangwoo")
                .email("jangwoopark@naver.com")
                .phoneNumber("01012345678")
                .build();
        userRepository.save(user);

        address = createAddress("장우네 집", true);

        addressRepository.save(address);

        request = new PostAddressRequest(
                address.getAddressName(),
                address.getReceiver(),
                address.getPhoneNumber(),
                address.getZipCode(),
                address.getBasicAddress(),
                address.getDetailAddress(),
                address.isDefaultAddress(),
                address.getRequest(),
                user.getUserId());
    }

    @Test
    void addAddress() {
        // given
        PostAddressResponse response = addressService.addAddress(request);

        // when
        Address findAddress = addressRepository.findAddressesByUserId(user.getUserId()).get(0);

        // then
        assertThat(findAddress).isEqualTo(address);
    }


    @Test
    void 기본_배송지_수정() {

        //given
        Address address2 = createAddress("Address2", false);
        addressRepository.save(address2);

        // 기존에 존재하는 address를 해제하고, address2를 기본 배송지로 수정하는 요청
        PatchAddressRequest patchRequest = new PatchAddressRequest(address2.getId(), address2.getUser().getUserId(), "request");
        PatchAddressResponse response = addressService.updateDefaultAddress(patchRequest);

        //when
        Address defaultAddress = addressRepository.findDefaultAddress(user.getUserId()).orElseThrow();

        //then
        assertThat(address2).isEqualTo(defaultAddress);

    }

    private Address createAddress(String addressName, boolean defaultAddress) {
        return Address.builder()
                .addressName(addressName)
                .receiver("박장우")
                .phoneNumber("01012345678")
                .zipCode("01234")
                .basicAddress("기본주소")
                .detailAddress("상세주소")
                .defaultAddress(defaultAddress)
                .request("요청사항")
                .user(user)
                .build();
    }


}