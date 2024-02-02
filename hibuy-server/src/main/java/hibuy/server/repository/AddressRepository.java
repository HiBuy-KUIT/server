package hibuy.server.repository;

import hibuy.server.domain.Address;
import hibuy.server.dto.address.AddressDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a JOIN FETCH a.user u WHERE u.userId = :user_id AND a.status = 'ACTIVE' AND u.status = 'ACTIVE'")
    List<Address> findAddressesByUserId(@Param("user_id") Long userId);
}
