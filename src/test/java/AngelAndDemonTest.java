import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.module_three.entity.Answer;
import org.module_three.entity.Question;
import org.module_three.entity.quests.AngelAndDemon;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AngelAndDemonTest {
    private AngelAndDemon angelAndDemon;
    private List<Question> questions;
    private List<Answer> answers;
    private String[] images;
    private String congratulation;
    private String congratulationImage;

    @BeforeEach
    void setUp() {
        // Создаем тестовые вопросы и ответы
        Question question1 = new Question(0, "What is 2 + 2?", "4. Answer 1.");
        Question question2 = new Question(1, "What is 3 + 5?", "8. Answer 2.");
        questions = Arrays.asList(question1, question2);

        Answer answer1 = new Answer(0, "4");
        Answer answer2 = new Answer(1, "8");
        answers = Arrays.asList(answer1, answer2);

        images = new String[] {"image1.png", "image2.png"};
        congratulation = "Congratulations!";
        congratulationImage = "congratulations_image.png";

        // Создаем объект AngelAndDemon
        angelAndDemon = new AngelAndDemon(
                "Angel and Demon",
                "Dark",
                "/path/to/quest",
                "Solve the riddles to determine your fate.",
                questions,
                answers,
                congratulation,
                congratulationImage,
                images
        );
    }

    @Test
    void testConstructorInitialization() {
        // Проверка, что все поля были корректно инициализированы
        assertEquals("Angel and Demon", angelAndDemon.getName());
        assertEquals("Dark", angelAndDemon.getBackground());
        assertEquals("/path/to/quest", angelAndDemon.getPath());
        assertEquals("Solve the riddles to determine your fate.", angelAndDemon.getDescription());
        assertEquals(2, angelAndDemon.getQuestions().size());
        assertEquals(2, angelAndDemon.getAnswers().size());
        assertEquals("Congratulations!", angelAndDemon.getCongratulation());
        assertEquals("congratulations_image.png", angelAndDemon.getCongratulationImage());
        assertArrayEquals(new String[] {"image1.png", "image2.png"}, angelAndDemon.getImagesQuestion());
    }

    @Test
    void testQuestionAndAnswerConsistency() {
        // Проверка, что количество вопросов и ответов совпадает
        assertEquals(questions.size(), answers.size(), "The number of questions should match the number of answers.");

        // Проверка, что каждый вопрос и его ответ соответствуют друг другу
        for (int i = 0; i < questions.size(); i++) {
            assertEquals(questions.get(i).getIdQuestion(), answers.get(i).getIdQuestion(), "Question and answer IDs should match.");
        }
    }

    @Test
    void testAnswerParsing() {
        // Проверка правильности парсинга ответа (разделение по точке)
        Question question = questions.get(0);
        String[] expectedAnswers = {"4", " Answer 1"};
        assertArrayEquals(expectedAnswers, question.getAnswer(), "The answer should be correctly split into parts.");
    }

    @Test
    void testEmptyImages() {
        // Проверяем, что изображения не пустые
        String[] emptyImages = new String[0];
        AngelAndDemon emptyImagesQuest = new AngelAndDemon(
                "Angel and Demon Empty",
                "Light",
                "/path/to/quest",
                "No images provided.",
                questions,
                answers,
                "No congratulation",
                "no_image.png",
                emptyImages
        );
        assertArrayEquals(new String[0], emptyImagesQuest.getImagesQuestion(), "Images should be empty.");
    }

    @Test
    void testNullCongratulation() {
        // Проверяем, что поздравление может быть null
        AngelAndDemon nullCongratulationQuest = new AngelAndDemon(
                "Angel and Demon Null Congratulation",
                "Light",
                "/path/to/quest",
                "Solve the riddles to determine your fate.",
                questions,
                answers,
                null,
                "congratulations_image.png",
                images
        );
        assertNull(nullCongratulationQuest.getCongratulation(), "Congratulation should be null.");
    }
}
