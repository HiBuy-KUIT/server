package hibuy.server.domain;

import static jakarta.persistence.FetchType.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class UserProduct extends BaseEntity{

    @Id
    @Column(name = "up_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int oneTakeAmount;

    private int totalAmount;

    private int notification;

    private int takeCount;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public UserProduct(int oneTakeAmount, int totalAmount, int notification,
            User user, Product product) {
        this.oneTakeAmount = oneTakeAmount;
        this.totalAmount = totalAmount;
        this.notification = notification;
        this.takeCount = 0;
        this.user = user;
        this.product = product;
        this.status = Status.ACTIVE;
    }

    public void updateUserProduct(int oneTakeAmount, int totalAmount, int notification) {
        this.oneTakeAmount = oneTakeAmount;
        this.totalAmount = totalAmount;
        this.notification = notification;
    }
}


