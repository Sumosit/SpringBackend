package PersonalArea.backend.repository;

import PersonalArea.backend.models.FileDB;
import PersonalArea.backend.models.StorageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageFileRepository extends JpaRepository<StorageFile, String> {

}
