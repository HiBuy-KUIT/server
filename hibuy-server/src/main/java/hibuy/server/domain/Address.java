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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "address_name")
    private String name;

    private String receiver;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "basic_address")
    private String basicAddress;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(nullable = false)
    private boolean isDefaultAddress;

    private String request;

    private String status;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Address(String name, String receiver, String phoneNumber, String zipCode, String basicAddress, String detailAddress, Boolean isDefaultAddress, String request, User user) {
        this.name = name;
        this.receiver = receiver;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.basicAddress = basicAddress;
        this.detailAddress = detailAddress;
        this.isDefaultAddress = isDefaultAddress;
        this.request = request;
        this.user = user;
        this.status = "ACTIVE";
    }

}
