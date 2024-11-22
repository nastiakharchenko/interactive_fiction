import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.module_three.entity.User;
import org.module_three.entity.UserCollection;

import static org.junit.jupiter.api.Assertions.*;

public class UserCollectionTest {
    private UserCollection userCollection;

    @BeforeEach
    void setUp() {
        userCollection = new UserCollection();
    }

    @Test
    void testSearchUserSuccess() {
        // Проверяем, что метод находит существующего пользователя
        assertTrue(userCollection.searchUser(new User("user1", "user1")), "User1 should be found");
        assertTrue(userCollection.searchUser(new User("user2", "user2")), "User2 should be found");
    }

    @Test
    void testSearchUserFailure() {
        // Проверяем, что метод не находит несуществующего пользователя
        assertFalse(userCollection.searchUser(new User("user7", "user7")), "Non-existent user should not be found");
    }

    @Test
    void testSearchUserNullUser() {
        // Проверяем, что передача null в searchUser вызовет ошибку
        assertThrows(NullPointerException.class, () -> userCollection.searchUser(null), "User cannot be null");
    }
}
