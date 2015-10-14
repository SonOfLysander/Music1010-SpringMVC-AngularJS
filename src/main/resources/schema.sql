CREATE TABLE music_questions
(
  id       INT          NOT NULL,
  question VARCHAR(300) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE music_answers
(
  id          INT          NOT NULL,
  question_id INT          NOT NULL,
  answer      VARCHAR(300) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (question_id) REFERENCES music_questions (id)
);