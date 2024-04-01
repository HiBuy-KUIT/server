package hibuy.server.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Enumerated(EnumType.STRING)
    protected Status status;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    protected Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    protected Timestamp updatedAt;

}
