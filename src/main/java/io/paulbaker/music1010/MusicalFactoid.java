package io.paulbaker.music1010;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by paul on 10/13/15.
 */
public class MusicalFactoid {

  private String question;
  private List<String> answer;

  public MusicalFactoid(String question, List<String> answer) {
    this.question = Objects.requireNonNull(question);
    this.answer = new ArrayList<>(answer.size());
    Collections.copy(this.answer, Objects.requireNonNull(answer));
  }

  public String getQuestion() {
    return question;
  }

  public List<String> getAnswer() {
    return answer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MusicalFactoid that = (MusicalFactoid) o;
    return Objects.equals(question, that.question) &&
      Objects.equals(answer, that.answer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(question, answer);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("MusicalFactoid{");
    sb.append("question='").append(question).append('\'');
    sb.append(", answer='").append(answer).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
