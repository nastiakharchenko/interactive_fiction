import org.junit.jupiter.api.Test;
import org.module_three.entity.Question;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {
    @Test
    public void testQuestionInitialization() {
        // Тестовые данные
        int idQuestion = 1;
        String questionText = "Что такое JUnit?";
        String answerText = "Это библиотека для тестирования.Она используется для написания тестов.";

        // Создание объекта Question
        Question question = new Question(idQuestion, questionText, answerText);

        // Проверка значений полей
        assertEquals(idQuestion, question.getIdQuestion(), "Question ID не совпадает");
        assertEquals(questionText, question.getQuestion(), "Question текст не совпадает");
        assertArrayEquals(new String[] {"Это библиотека для тестирования", "Она используется для написания тестов"},
                question.getAnswer(), "Answer массив не совпадает");
    }

    @Test
    public void testAnswerSplittingSinglePart() {
        // Ответ, не содержащий точек
        int idQuestion = 2;
        String questionText = "Какой язык программирования используется в JUnit?";
        String answerText = "Java";

        Question question = new Question(idQuestion, questionText, answerText);

        // Проверка, что ответ разделился на один элемент
        assertArrayEquals(new String[] {"Java"}, question.getAnswer(), "Answer массив должен содержать один элемент");
    }

    @Test
    public void testAnswerSplittingMultipleParts() {
        // Ответ с несколькими предложениями
        int idQuestion = 3;
        String questionText = "Для чего используется JUnit?";
        String answerText = "JUnit используется для написания тестов.Он поддерживает аннотации, такие как @Test и @BeforeEach.";

        Question question = new Question(idQuestion, questionText, answerText);

        // Проверка, что ответ разделился на массив
        assertArrayEquals(new String[] {"JUnit используется для написания тестов", "Он поддерживает аннотации, такие как @Test и @BeforeEach"},
                question.getAnswer(), "Answer массив не совпадает");
    }

    @Test
    public void testEmptyAnswer() {
        // Пустой ответ
        int idQuestion = 4;
        String questionText = "Что такое пустой ответ?";
        String answerText = "";

        Question question = new Question(idQuestion, questionText, answerText);

        // Проверка, что массив пустой
        assertArrayEquals(new String[] {""}, question.getAnswer(), "Answer массив должен содержать пустую строку");
    }

    @Test
    public void testAnswerWithTrailingDot() {
        // Ответ с точкой в конце
        int idQuestion = 5;
        String questionText = "Почему важна точка?";
        String answerText = "Ответ с точкой в конце.";

        Question question = new Question(idQuestion, questionText, answerText);

        // Проверка, что точка в конце обработана корректно
        assertArrayEquals(new String[] {"Ответ с точкой в конце"}, question.getAnswer(), "Answer массив должен обрабатывать точку корректно");
    }
}
