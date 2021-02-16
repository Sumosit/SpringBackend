package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalData, String> {
}
