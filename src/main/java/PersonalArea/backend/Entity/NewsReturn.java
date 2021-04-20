package PersonalArea.backend.Entity;

import javax.persistence.Column;
import java.sql.Timestamp;

public class NewsReturn {
  private Long authorId;
  private String authorName;
  private FileDB authorAvatar;
  private String title;
  private String content;
  private Timestamp date;

  public NewsReturn(Long authorId, String authorName, FileDB authorAvatar, String title, String content, Timestamp date) {
    this.authorId = authorId;
    this.authorName = authorName;
    this.authorAvatar = authorAvatar;
    this.title = title;
    this.content = content;
    this.date = date;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public FileDB getAuthorAvatar() {
    return authorAvatar;
  }

  public void setAuthorAvatar(FileDB authorAvatar) {
    this.authorAvatar = authorAvatar;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Timestamp getDate() {
    return date;
  }

  public void setDate(Timestamp date) {
    this.date = date;
  }
}
