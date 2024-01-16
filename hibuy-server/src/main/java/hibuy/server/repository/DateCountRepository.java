package hibuy.server.repository;

import hibuy.server.domain.DateCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateCountRepository extends JpaRepository<DateCount, Long> {
}
