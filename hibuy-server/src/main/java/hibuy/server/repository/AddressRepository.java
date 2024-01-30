package hibuy.server.repository;

import hibuy.server.domain.Address;
import hibuy.server.dto.address.AddressDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT new hibuy.server.dto.address.AddressDTO(a.id, a.addressName, a.receiver, a.phoneNumber, a.basicAddress, a.detailAddress, a.isDefaultAddress)" +
            " FROM Address a" +
            " JOIN FETCH a.user u" +
            " WHERE u.userId = :user_id AND a.status = 'ACTIVE' AND u.status = 'ACTIVE'")
    List<AddressDTO> findAddressesByUserId(@Param("user_id") Long userId);
}
