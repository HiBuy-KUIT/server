package hibuy.server.service;

import hibuy.server.domain.Address;
import hibuy.server.domain.User;
import hibuy.server.dto.address.*;
import hibuy.server.repository.AddressRepository;
import hibuy.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

        if (request.getDefaultAddress()){
            disablePrevDefaultAddress();
        }

        Address address = addressRepository.save(new Address(
                request.getAddressName(),
                request.getReceiver(),
                request.getPhoneNumber(),
                request.getZipCode(),
                request.getBasicAddress(),
                request.getDetailAddress(),
                request.getDefaultAddress(),
                request.getRequest(),
                user
        ));

        return new PostAddressResponse(address.getId());

    }


    public GetAddressListResponse getAddressListById(Long userId) {
        List<Address> addresses = addressRepository.findAddressesByUserId(userId);
        List<AddressDTO> addressDTOs = addresses.stream().map(AddressDTO::new).collect(toList());
        return new GetAddressListResponse(addressDTOs);
    }


    public PatchAddressResponse updateDefaultAddress(PatchAddressRequest request) {

        disablePrevDefaultAddress();

        Address newAddress = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found with id " + request.getAddressId()));

        newAddress.updateAddress(true, request.getRequest());
        return new PatchAddressResponse(newAddress.getId());
        
    }

    protected void disablePrevDefaultAddress() {
        Address currentDefaultAddress = addressRepository.findDefaultAddress();
        currentDefaultAddress.updateAddress(false, currentDefaultAddress.getRequest());
    }
}
