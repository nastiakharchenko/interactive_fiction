import org.junit.jupiter.api.Test;
import org.module_three.entity.Answer;
import org.module_three.entity.quests.QuestCollection;
import org.module_three.entity.Question;
import org.module_three.entity.User;
import org.module_three.entity.quests.AngelAndDemon;
import org.module_three.service.JsonService;

import static org.junit.jupiter.api.Assertions.*;
import static org.module_three.constant.FileNameConstants.*;

import java.io.IOException;
import java.util.List;

public class JsonServiceTest {
    @Test
    public void testReadFileJsonUser() throws IOException {
        List<User> users = JsonService.readFileJsonUser(FILE_USERS);

        assertNotNull(users);
        assertEquals(5, users.size());

        assertEquals("user1", users.get(0).getUsername());
        assertEquals("user1", users.get(0).getPassword());
        assertEquals("user5", users.get(4).getUsername());
        assertEquals("user5", users.get(4).getPassword());
    }

    @Test
    public void testReadFileJsonQuests() {
        List<QuestCollection> quests = JsonService.readFileJsonQuests(FILE_QUESTS);

        assertNotNull(quests);
        assertEquals(1, quests.size());

        QuestCollection quest = quests.get(0);
        assertEquals("Ангели та демони", quest.getName());
        assertEquals("image/angel_and_demon/angel_and_demon.jpg", quest.getBackground());
        assertEquals("/angel_and_demon", quest.getPath());
        assertTrue(quest.getDescription().contains("Тобі хоч раз в житті хотілося стати детективом?"));
    }

    @Test
    public void testReadFileJsonQuestAngelAndDemon() {
        AngelAndDemon angelAndDemon = JsonService.readFileJsonQuestAngelAndDemon(FILE_QUEST_ANGEL_AND_DEMON);

        assertNotNull(angelAndDemon);
        assertEquals("Ангели та демони", angelAndDemon.getName());
        assertEquals("image/angel_and_demon/angel_and_demon.jpg", angelAndDemon.getBackground());
        assertEquals("/angel_and_demon", angelAndDemon.getPath());

        // Проверка вопросов
        List<Question> questions = angelAndDemon.getQuestions();
        assertEquals(5, questions.size());
        Question firstQuestion = questions.get(0);
        assertTrue(firstQuestion.getQuestion().contains("Хто такі ілюмінати?"));
        assertEquals(2, firstQuestion.getAnswer().length); // Проверка, что первый вопрос содержит два возможных ответа
        assertEquals("Пантеон", firstQuestion.getAnswer()[0]);
        assertEquals("Санта-Марія-дель-Пополо", firstQuestion.getAnswer()[1]);

        // Проверка ответов
        List<Answer> answers = angelAndDemon.getAnswers();
        assertEquals(5, answers.size());
        assertEquals("Санта-Марія-дель-Пополо", answers.get(0).getAnswer());

        // Проверка изображений
        String[] images = angelAndDemon.getImagesQuestion();
        assertEquals(5, images.length);
        assertEquals("image/angel_and_demon/secret_archive.jpeg", images[0]);

        // Проверка поздравления
        assertEquals("Ти зробив це! Ілюмінати готові тебе прийняти. Ти пройшов 'шляхом просвітлення' та знайшов таємне місце зустрічі.", angelAndDemon.getCongratulation());
        assertEquals("image/angel_and_demon/Engelsburg_und_Engelsbrücke_abends.jpg", angelAndDemon.getCongratulationImage());
    }
}
