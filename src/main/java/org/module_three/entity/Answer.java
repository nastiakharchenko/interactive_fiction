package org.module_three.entity;

import lombok.Getter;

@Getter
public class Answer {
    private final int idQuestion;
    private final String answer;

    public Answer(int idQuestion, String answer) {
        this.idQuestion = idQuestion;
        this.answer = answer;
    }
}
