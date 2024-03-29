package hibuy.server.controller;

import hibuy.server.common.response.BaseResponse;
import hibuy.server.dto.address.*;
import hibuy.server.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public BaseResponse<PostAddressResponse> addAddress(@Validated @RequestBody PostAddressRequest postAddressRequest){
        log.debug("[AddressController.addAddress]");
        return new BaseResponse<>(addressService.addAddress(postAddressRequest));
    }

    @GetMapping
    public BaseResponse<GetAddressListResponse> getAddressList(@RequestParam Long userId){
        log.debug("[AddressController.getAddressList]");
        return new BaseResponse<>(addressService.getAddressListById(userId));
    }

    @PatchMapping
    public BaseResponse<PatchAddressResponse> updateDefaultAddress(@Validated @RequestBody PatchAddressRequest request){
        log.debug("[AddressController.updateAddress]");
        return new BaseResponse<>(addressService.updateDefaultAddress(request));

    }

}
