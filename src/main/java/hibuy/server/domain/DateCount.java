package hibuy.server.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class DateCount extends BaseEntity {

    @Id
    @Column(name = "dc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dateCountId;

    @Column(name = "date_count", nullable = false)
    private int dateCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public DateCount(User user) {
        this.dateCount = 0;
        this.user = user;
        this.status = Status.ACTIVE;
    }

    public void increaseDateCount() {
        this.dateCount += 1;
    }
}
