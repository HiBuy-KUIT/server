package hibuy.server.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "name")
    private String addressName;

    private String receiver;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "basic_address")
    private String basicAddress;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "default_address", nullable = false)
    private boolean defaultAddress;

    private String request;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    private Address(String addressName, String receiver, String phoneNumber, String zipCode, String basicAddress, String detailAddress, Boolean defaultAddress, String request, User user) {
        super();
        this.addressName = addressName;
        this.receiver = receiver;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.basicAddress = basicAddress;
        this.detailAddress = detailAddress;
        this.defaultAddress = defaultAddress;
        this.request = request;
        this.user = user;
    }

    public static Address of(String addressName, String receiver, String phoneNumber, String zipCode, String basicAddress, String detailAddress, Boolean defaultAddress, String request, User user) {
        return Address.builder()
                .addressName(addressName)
                .receiver(receiver)
                .phoneNumber(phoneNumber)
                .zipCode(zipCode)
                .basicAddress(basicAddress)
                .detailAddress(detailAddress)
                .defaultAddress(defaultAddress)
                .request(request)
                .user(user)
                .build();
    }

    public void updateAddress(Boolean isDefaultAddress, String request) {
        this.defaultAddress = isDefaultAddress;
        this.request = request;
    }

}
