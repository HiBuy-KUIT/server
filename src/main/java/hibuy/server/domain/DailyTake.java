package hibuy.server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyTake extends BaseEntity{

    @Id
    @Column(name = "dt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyTakeId;

    @Column(name = "take_date", nullable = false)
    private Date takeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public DailyTake(Date takeDate, User user) {
        super();
        this.takeDate = takeDate;
        this.user = user;
    }

    public boolean isStatusInactive() {
        return this.status.equals(Status.INACTIVE);
    }

    public void changeStatusToActive() {
        this.status = Status.ACTIVE;
    }
}
