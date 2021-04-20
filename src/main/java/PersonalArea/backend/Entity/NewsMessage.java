package PersonalArea.backend.Entity;

import javax.persistence.Column;

public class NewsMessage {

  private Long authorId;
  @Column(length = 1000)
  private String title;
  @Column(length = 3000)
  private String content;
  private String date;

  public NewsMessage(Long authorId, String title, String content, String date) {
    this.authorId = authorId;
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
