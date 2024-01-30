package hibuy.server.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class DailyTake {

    @Id
    @Column(name = "dt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyTakeId;

    @Column(name = "take_date", nullable = false)
    private Date takeDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public DailyTake(Date takeDate, User user) {
        this.takeDate = takeDate;
        this.status = Status.ACTIVE;
        this.user = user;
    }
}
