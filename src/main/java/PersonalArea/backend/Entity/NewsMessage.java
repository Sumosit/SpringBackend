package PersonalArea.backend.Entity;

import javax.persistence.Column;

public class NewsMessage {

  private Long userId;
  private Long authorId;
  private String authorUsername;
  private String authorAvatar;
  @Column(length = 1000)
  private String title;
  @Column(length = 3000)
  private String content;
  private String date;

  public NewsMessage(Long userId, Long authorId, String authorUsername, String authorAvatar, String title, String content, String date) {
    this.userId = userId;
    this.authorId = authorId;
    this.authorUsername = authorUsername;
    this.authorAvatar = authorAvatar;
    this.title = title;
    this.content = content;
    this.date = date;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public String getAuthorUsername() {
    return authorUsername;
  }

  public void setAuthorUsername(String authorUsername) {
    this.authorUsername = authorUsername;
  }

  public String getAuthorAvatar() {
    return authorAvatar;
  }

  public void setAuthorAvatar(String authorAvatar) {
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

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
