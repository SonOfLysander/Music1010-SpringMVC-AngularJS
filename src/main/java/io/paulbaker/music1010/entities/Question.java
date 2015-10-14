package io.paulbaker.music1010.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;


/**
 * Created by paul on 10/13/15.
 */
@Entity
@Table(name = "music_question")
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private long questionId;

  @Column(name = "question")
  private String question;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = Answer.class, fetch = FetchType.EAGER)
  @JoinColumn(insertable = true, updatable = true, referencedColumnName = "answers")
  private List<Answer> answers;

  public Question(String question, List<Answer> answers) {
    this.question = Objects.requireNonNull(question);
    this.answers = Objects.requireNonNull(answers);
  }

  public long getQuestionId() {
    return questionId;
  }

  public String getQuestion() {
    return question;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Question that = (Question) o;
    return Objects.equals(question, that.question) &&
      Objects.equals(answers, that.answers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(question, answers);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Question{");
    sb.append("question='").append(question).append('\'');
    sb.append(", answers='").append(answers).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
