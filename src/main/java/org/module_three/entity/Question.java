package org.module_three.entity;

import lombok.Getter;

@Getter
public class Question {
    private final int idQuestion;
    private final String question;
    private final String[] answer;

    public Question(int idQuestion, String question, String answer) {
        this.idQuestion = idQuestion;
        this.question = question;
        this.answer = setAnswer(answer);
    }

    private String[] setAnswer(String answer) {
        return answer.split("\\.");
    }
}
