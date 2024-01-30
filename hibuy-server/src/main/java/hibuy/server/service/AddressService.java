package hibuy.server.service;

import hibuy.server.domain.Address;
import hibuy.server.domain.User;
import hibuy.server.dto.address.PostAddressRequest;
import hibuy.server.dto.address.PostAddressResponse;
import hibuy.server.repository.AddressRepository;
import hibuy.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public PostAddressResponse addAddress(PostAddressRequest request) {

        log.debug("[AddressService.addAddress]");

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        Address address = addressRepository.save(new Address(
                request.getAddressName(),
                request.getReceiver(),
                request.getPhoneNumber(),
                request.getZipCode(),
                request.getBasicAddress(),
                request.getDetailAddress(),
                request.getIsDefaultAddress(),
                request.getRequest(),
                user
        ));

        return new PostAddressResponse(address.getId());

    }




}