package hibuy.server.service;

import hibuy.server.common.exception.notfound.NotFoundAddressException;
import hibuy.server.common.exception.notfound.NotFoundUserException;
import hibuy.server.domain.Address;
import hibuy.server.domain.User;
import hibuy.server.dto.address.*;
import hibuy.server.repository.AddressRepository;
import hibuy.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
                .orElseThrow(NotFoundUserException::new);

        if (request.getDefaultAddress()) {
            disablePrevDefaultAddress(request.getUserId());
        }

        Address address = addressRepository.save(request.toEntity(user));

        return new PostAddressResponse(address.getId());

    }


    public GetAddressListResponse getAddressListById(Long userId) {
        List<Address> addresses = addressRepository.findAddressesByUserId(userId);
        List<AddressDTO> addressDTOs = addresses.stream().map(AddressDTO::new).collect(toList());
        return new GetAddressListResponse(addressDTOs);
    }

    @Transactional
    public PatchAddressResponse updateDefaultAddress(PatchAddressRequest request) {

        disablePrevDefaultAddress(request.getUserId());

        Address newAddress = addressRepository.findById(request.getAddressId())
                .orElseThrow(NotFoundAddressException::new);

        newAddress.updateAddress(true, request.getRequest());

        return new PatchAddressResponse(newAddress.getId());

    }

    protected void disablePrevDefaultAddress(Long userId) {
        Optional<Address> addressOptional = addressRepository.findDefaultAddress(userId);
        if (addressOptional.isPresent()) {
            Address currentDefaultAddress = addressOptional.get();
            currentDefaultAddress.updateAddress(false, currentDefaultAddress.getRequest());
        }
    }
}
