package hibuy.server.service;

import hibuy.server.domain.Address;
import hibuy.server.domain.User;
import hibuy.server.dto.address.PostAddressRequest;
import hibuy.server.dto.address.PostAddressResponse;
import hibuy.server.repository.AddressRepository;
import hibuy.server.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        user = new User("jangwoo", "jangwoopark@naver.com", "010-1234-5678");
        userRepository.save(user);

        address = new Address("장우네 집", "박장우", "010-1234-5678", "01234", "기본주소", "상세주소", true, "요청사항", user);
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
}