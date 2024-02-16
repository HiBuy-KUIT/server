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

    private String productName;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;



    public UserProduct(int oneTakeAmount, int totalAmount, int notification, String productName,
            User user) {
        this.oneTakeAmount = oneTakeAmount;
        this.totalAmount = totalAmount;
        this.notification = notification;
        this.takeCount = 0;
        this.productName = productName;
        this.user = user;
        this.status = Status.ACTIVE;
    }

    public void updateUserProduct(int oneTakeAmount, int totalAmount, int notification) {
        this.oneTakeAmount = oneTakeAmount;
        this.totalAmount = totalAmount;
        this.notification = notification;
    }
}


