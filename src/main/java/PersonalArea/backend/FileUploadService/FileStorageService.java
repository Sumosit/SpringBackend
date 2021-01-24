package PersonalArea.backend.FileUploadService;

import PersonalArea.backend.models.FileDB;
import PersonalArea.backend.models.StorageFile;
import PersonalArea.backend.repository.FileDBRepository;
import PersonalArea.backend.repository.StorageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {

  @Autowired
  private FileDBRepository fileDBRepository;

  @Autowired
  private StorageFileRepository storageFileRepository;

  public FileDB store(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    FileDB FileDB = new FileDB(null, fileName, file.getContentType(), file.getBytes());
    return fileDBRepository.save(FileDB);
  }

  public StorageFile storeStorageFile(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    StorageFile storageFile = new StorageFile(null, fileName, file.getContentType(), file.getBytes());
    return storageFileRepository.save(storageFile);
  }

  public FileDB getFile(String id) {
    return fileDBRepository.findById(id).get();
  }
  public StorageFile getStorageFile(String id) {
    return storageFileRepository.findById(id).get();
  }

  public Stream<FileDB> getAllFiles() {
    return fileDBRepository.findAll().stream();
  }
  public Stream<StorageFile> getAllStorageFiles() {
    return storageFileRepository.findAll().stream();
  }
}