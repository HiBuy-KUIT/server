package hibuy.server.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Address extends BaseEntity{

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
    private boolean isDefaultAddress;

    private String request;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Address(String addressName, String receiver, String phoneNumber, String zipCode, String basicAddress, String detailAddress, Boolean isDefaultAddress, String request, User user) {
        this.addressName = addressName;
        this.receiver = receiver;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.basicAddress = basicAddress;
        this.detailAddress = detailAddress;
        this.isDefaultAddress = isDefaultAddress;
        this.request = request;
        this.user = user;
        this.status = Status.ACTIVE;
    }

}
