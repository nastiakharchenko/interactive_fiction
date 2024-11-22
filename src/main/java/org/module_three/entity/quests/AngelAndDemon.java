package org.module_three.entity.quests;

import lombok.Getter;
import org.module_three.entity.Answer;
import org.module_three.entity.Quest;
import org.module_three.entity.Question;

import java.util.List;

@Getter
public class AngelAndDemon extends Quest {
    private final String[] imagesQuestion;

    public AngelAndDemon(String name, String background, String path, String description, List<Question> questions, List<Answer> answers, String congratulation, String congratulationImage, String[] imagesQuestion) {
        super(name, background, path, description, questions, answers, congratulation, congratulationImage);
        this.imagesQuestion = imagesQuestion;
    }
}
