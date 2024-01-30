package hibuy.server.controller;

import hibuy.server.common.response.BaseResponse;
import hibuy.server.dto.address.PostAddressRequest;
import hibuy.server.dto.address.PostAddressResponse;
import hibuy.server.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public BaseResponse<PostAddressResponse> addAddress(@RequestBody PostAddressRequest postAddressRequest){
        log.debug("[AddressController.addAddress]");
        return new BaseResponse<>(addressService.addAddress(postAddressRequest));
    }

}
