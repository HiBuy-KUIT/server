package hibuy.server.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@MappedSuperclass
@Getter
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
