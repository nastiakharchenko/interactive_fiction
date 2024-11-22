import org.junit.jupiter.api.Test;
import org.module_three.entity.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerTest {
    @Test
    public void testAnswerInitialization() {
        // Создаем объект Answer с тестовыми данными
        int idQuestion = 1;
        String answer = "Тестовый ответ";

        Answer answerObj = new Answer(idQuestion, answer);

        // Проверяем, что поля инициализированы корректно
        assertEquals(idQuestion, answerObj.getIdQuestion(), "Question ID does not match");
        assertEquals(answer, answerObj.getAnswer(), "Answer text does not match");
    }

    @Test
    public void testAnswerEmptyString() {
        // Тест с пустым ответом
        int idQuestion = 2;
        String answer = "";

        Answer answerObj = new Answer(idQuestion, answer);

        // Проверяем, что ответ пустой
        assertEquals(answer, answerObj.getAnswer(), "Answer should be empty");
    }

    @Test
    public void testAnswerSpecialCharacters() {
        // Тест с символами в ответе
        int idQuestion = 3;
        String answer = "@#$% Специальные символы в ответе";

        Answer answerObj = new Answer(idQuestion, answer);

        // Проверяем корректность инициализации с символами
        assertEquals(answer, answerObj.getAnswer(), "Answer with special characters does not match");
    }

    @Test
    public void testAnswerNegativeId() {
        // Тест с отрицательным ID вопроса
        int idQuestion = -1;
        String answer = "Ответ с отрицательным ID";

        Answer answerObj = new Answer(idQuestion, answer);

        // Проверяем, что ID установлен корректно даже при отрицательном значении
        assertEquals(idQuestion, answerObj.getIdQuestion(), "Negative ID does not match");
    }
}
