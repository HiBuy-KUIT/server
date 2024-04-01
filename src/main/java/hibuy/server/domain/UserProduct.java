package hibuy.server.domain;

import static jakarta.persistence.FetchType.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProduct extends BaseEntity{

    @Id
    @Column(name = "up_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int oneTakeAmount;
    private int totalAmount;

    private int notification;

    private int takeCount;

    private String productName;

    private String companyName;
    private String imageUrl;


    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    private UserProduct(int oneTakeAmount, int totalAmount, int notification, String productName,
            User user, String companyName, String imageUrl, int takeCount) {
        super();
        this.oneTakeAmount = oneTakeAmount;
        this.totalAmount = totalAmount;
        this.notification = notification;
        this.takeCount = takeCount;
        this.productName = productName;
        this.user = user;
        this.companyName = companyName;
        this.imageUrl = imageUrl;
    }

    public static UserProduct createUserProductWithZeroTakeCount(
            int oneTakeAmount, int totalAmount, int notification, String productName,
            User user, String companyName, String imageUrl) {
        return UserProduct.builder()
                .oneTakeAmount(oneTakeAmount)
                .totalAmount(totalAmount)
                .notification(notification)
                .takeCount(0)
                .productName(productName)
                .user(user)
                .companyName(companyName)
                .imageUrl(imageUrl)
                .build();
    }

    public void updateUserProduct(int oneTakeAmount, int totalAmount, int notification) {
        this.oneTakeAmount = oneTakeAmount;
        this.totalAmount = totalAmount;
        this.notification = notification;
    }
}


