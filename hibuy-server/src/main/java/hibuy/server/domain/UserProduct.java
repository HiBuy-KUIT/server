package hibuy.server.domain;

import static jakarta.persistence.FetchType.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class UserProduct {

    @Id
    @Column(name = "up_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int oneTakeAmount;

    private int totalAmount;

    private int notification;

    private int takeCount;

    private String status;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp createdAt;

    @LastModifiedDate
    private Timestamp updatedAt;

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
        this.status = "ACTIVE";
        this.user = user;
        this.product = product;
    }

    public void updateUserProduct(int oneTakeAmount, int totalAmount, int notification) {
        this.oneTakeAmount = oneTakeAmount;
        this.totalAmount = totalAmount;
        this.notification = notification;
    }
}


