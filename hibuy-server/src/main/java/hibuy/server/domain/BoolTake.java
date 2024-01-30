package hibuy.server.domain;

import static jakarta.persistence.FetchType.*;

import jakarta.persistence.*;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BoolTake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bt_id")
    private Long id;

    private Timestamp takeDateTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "up_id")
    private UserProduct userProduct;

    public BoolTake(Timestamp takeDateTime, Status status, UserProduct userProduct) {
        this.takeDateTime = takeDateTime;
        this.status = status;
        this.userProduct = userProduct;
    }

    public void updateBoolTake(Status status) {
        this.status = status;
    }
}
