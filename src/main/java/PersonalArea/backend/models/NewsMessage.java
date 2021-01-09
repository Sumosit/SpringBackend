package PersonalArea.backend.models;

import javax.persistence.Column;

public class NewsMessage {

  @Column(length = 1000)
  private String title;
  @Column(length = 3000)
  private String content;

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
}
