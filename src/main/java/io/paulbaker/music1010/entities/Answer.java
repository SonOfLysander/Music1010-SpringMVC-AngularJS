package io.paulbaker.music1010.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Created by paul on 10/14/15.
 */
@Entity
@Table(name = "music_answer")
public class Answer {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private long answerId;

  //  @Column()
  @ManyToOne(targetEntity = Question.class, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "question_id", referencedColumnName = "id", insertable = true, updatable = true)
  private Question question;

  @Column(name = "answer")
  private String answer;

  public long getAnswerId() {
    return answerId;
  }

  public Question getQuestion() {
    return question;
  }

  public String getAnswer() {
    return answer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Answer answer1 = (Answer) o;
    return Objects.equals(answerId, answer1.answerId) &&
      Objects.equals(question, answer1.question) &&
      Objects.equals(answer, answer1.answer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(answerId, question, answer);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Answer{");
    sb.append("answerId=").append(answerId);
    sb.append(", question=").append(question);
    sb.append(", answer='").append(answer).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
