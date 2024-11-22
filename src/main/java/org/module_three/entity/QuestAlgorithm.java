package org.module_three.entity;

import java.util.List;

public interface QuestAlgorithm {
    List<Question> getQuestions();
    List<Answer> getAnswers();
    String getCongratulation();
    String getCongratulationImage();
}