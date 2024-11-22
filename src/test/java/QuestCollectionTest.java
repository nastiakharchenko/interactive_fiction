import org.junit.jupiter.api.Test;
import org.module_three.entity.Answer;
import org.module_three.entity.Question;
import org.module_three.entity.quests.QuestCollection;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestCollectionTest {
    @Test
    public void testQuestListInitialization() {
        // Создаем объект QuestList с тестовыми данными
        String name = "Тестовый квест";
        String background = "background.jpg";
        String path = "/test_path";
        String description = "Описание тестового квеста";
        List<Question> questions = new ArrayList<>(); // Инициализация тестовых данных
        List<Answer> answers = new ArrayList<>();
        String congratulation = "Поздравляем!";
        String congratulationImage = "congratulation.jpg";

        QuestCollection quest = new QuestCollection(name, background, path, description, questions, answers, congratulation, congratulationImage);

        // Проверяем, что поля инициализированы корректно
        assertEquals(name, quest.getName(), "Quest name does not match");
        assertEquals(background, quest.getBackground(), "Quest background does not match");
        assertEquals(path, quest.getPath(), "Quest path does not match");
        assertEquals(description, quest.getDescription(), "Quest description does not match");
        assertEquals(questions, quest.getQuestions(), "Quest questions do not match");
        assertEquals(answers, quest.getAnswers(), "Quest answers do not match");
        assertEquals(congratulation, quest.getCongratulation(), "Quest congratulation does not match");
        assertEquals(congratulationImage, quest.getCongratulationImage(), "Quest congratulation image does not match");
    }

    @Test
    public void testQuestListEmptyDescription() {
        // Тест с пустым описанием
        String name = "Без описания";
        String background = "background.jpg";
        String path = "/empty_description";
        String description = "";
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        String congratulation = "Поздравляем!";
        String congratulationImage = "congratulation.jpg";

        QuestCollection quest = new QuestCollection(name, background, path, description, questions, answers, congratulation, congratulationImage);

        // Проверяем корректную инициализацию с пустым описанием
        assertEquals(description, quest.getDescription(), "Quest description should be empty");
    }

    @Test
    public void testQuestListSpecialCharacters() {
        // Тест с символами в полях
        String name = "Тестовый квест с символами @#$%";
        String background = "background_special.jpg";
        String path = "/path_with_special_@#$";
        String description = "Описание с символами @#$%";
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        String congratulation = "Поздравляем с @#$%";
        String congratulationImage = "congratulation_special.jpg";

        QuestCollection quest = new QuestCollection(name, background, path, description, questions, answers, congratulation, congratulationImage);

        // Проверяем корректную инициализацию с символами
        assertEquals(name, quest.getName(), "Quest name with special characters does not match");
        assertEquals(background, quest.getBackground(), "Quest background with special characters does not match");
        assertEquals(path, quest.getPath(), "Quest path with special characters does not match");
        assertEquals(description, quest.getDescription(), "Quest description with special characters does not match");
        assertEquals(congratulation, quest.getCongratulation(), "Quest congratulation with special characters does not match");
        assertEquals(congratulationImage, quest.getCongratulationImage(), "Quest congratulation image with special characters does not match");
    }
}
