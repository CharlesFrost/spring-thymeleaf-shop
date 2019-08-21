package online.patologia.czaropedal.repo;

import online.patologia.czaropedal.model.AddressAndPersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<AddressAndPersonalData,Long> {
}
