package org.module_three.entity;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class Quest implements QuestAlgorithm{
    private final String name;
    private final String background;
    private final String path;
    private final String description;
    private final List<Question> questions;
    private final List<Answer> answers;
    private final String congratulation;
    private final String congratulationImage;

    public Quest(String name, String background, String path, String description, List<Question> questions, List<Answer> answers, String congratulation, String congratulationImage) {
        this.name = name;
        this.background = background;
        this.path = path;
        this.description = description;
        this.questions = questions;
        this.answers = answers;
        this.congratulation = congratulation;
        this.congratulationImage = congratulationImage;
    }
}
