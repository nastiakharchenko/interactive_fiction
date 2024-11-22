import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.module_three.entity.quests.QuestCollection;
import org.module_three.service.FirstProgramDataLoader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FirstProgramDataLoaderTest {
    private FirstProgramDataLoader firstProgramDataLoader;

    @BeforeEach
    public void setUp() {
        firstProgramDataLoader = new FirstProgramDataLoader();
    }

    @Test
    public void testQuestsNotNull() {
        // Проверяем, что список quests не null после инициализации
        assertNotNull(firstProgramDataLoader.getQuests(), "Quests list should not be null after initialization");
    }

    @Test
    public void testQuestsSize() {
        // Проверяем размер списка квестов
        List<QuestCollection> quests = firstProgramDataLoader.getQuests();
        assertEquals(1, quests.size(), "Quests list should contain exactly one quest");
    }

    @Test
    public void testQuestAttributes() {
        // Проверяем атрибуты первого квеста
        QuestCollection quest = firstProgramDataLoader.getQuests().get(0);
        assertEquals("Ангели та демони", quest.getName(), "Quest name does not match");
        assertEquals("image/angel_and_demon/angel_and_demon.jpg", quest.getBackground(), "Quest background does not match");
        assertEquals("/angel_and_demon", quest.getPath(), "Quest path does not match");
        assertTrue(quest.getDescription().contains("Тобі хоч раз в житті хотілося стати детективом?"), "Quest description does not match");
    }

    @Test
    public void testLoadQuestsExceptionHandling() {
        // Проверка на случай выброса исключения IOException
        try {
            FirstProgramDataLoader programData = new FirstProgramDataLoader();
            assertNotNull(programData.getQuests(), "Quests list should not be null even if an exception occurs");
        } catch (Exception e) {
            fail("Exception should not be thrown during StartProgramData initialization");
        }
    }
}
