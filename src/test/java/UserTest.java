import org.junit.jupiter.api.Test;
import org.module_three.entity.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    public void testUserInitialization() {
        // Тестовые данные
        String username = "testUser";
        String password = "password123";

        // Создание объекта User
        User user = new User(username, password);

        // Проверка значений полей
        assertEquals(username, user.getUsername(), "Username не совпадает");
        assertEquals(password, user.getPassword(), "Password не совпадает");
    }

    @Test
    public void testEmptyUsername() {
        // Пустое имя пользователя
        String username = "";
        String password = "password123";

        // Создание объекта User
        User user = new User(username, password);

        // Проверка значений полей
        assertEquals(username, user.getUsername(), "Username должен быть пустым");
        assertEquals(password, user.getPassword(), "Password не совпадает");
    }

    @Test
    public void testEmptyPassword() {
        // Пустой пароль
        String username = "testUser";
        String password = "";

        // Создание объекта User
        User user = new User(username, password);

        // Проверка значений полей
        assertEquals(username, user.getUsername(), "Username не совпадает");
        assertEquals(password, user.getPassword(), "Password должен быть пустым");
    }

    @Test
    public void testNullUsername() {
        // Проверка на `null` в username
        String password = "password123";

        // Создание объекта User
        User user = new User(null, password);

        // Проверка значений полей
        assertEquals(user.getUsername(), "",  "Username должен быть пустым");
    }

    @Test
    public void testNullPassword() {
        // Проверка на `null` в password
        String username = "testUser";

        // Создание объекта User
        User user = new User(username, null);

        // Проверка значений полей
        assertEquals(user.getPassword(), "", "Password должен быть пустым");
    }
}
