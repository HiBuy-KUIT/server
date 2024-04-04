package hibuy.server.domain;

import static jakarta.persistence.FetchType.*;

import jakarta.persistence.*;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoolTake extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bt_id")
    private Long id;

    private Timestamp takeDateTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "up_id")
    private UserProduct userProduct;

    public BoolTake(Timestamp takeDateTime, Status status, UserProduct userProduct) {
        this.takeDateTime = takeDateTime;
        this.userProduct = userProduct;
        this.status = status;
    }

    public void updateBoolTake(Status status) {
        this.status = status;
    }
}
