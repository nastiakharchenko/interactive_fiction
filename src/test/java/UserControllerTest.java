import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.module_three.controller.UserController;
import org.module_three.entity.User;
import org.module_three.entity.UserCollection;

//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private UserCollection users;

    private final String USERNAME = "testUser";
    private final String PASSWORD = "testPassword";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        // Инициализируем мок UserCollection, чтобы избежать реальной работы с файлами
        users = mock(UserCollection.class);
    }

    @Test
    void testSuccessfulRegistration() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("Реєстрація");
        when(request.getParameter("username")).thenReturn(USERNAME);
        when(request.getParameter("password")).thenReturn(PASSWORD);
        when(users.searchUsername(USERNAME)).thenReturn(false); // Пользователь не существует

        // Мокируем добавление пользователя
        doNothing().when(users).getUsers().add(any(User.class));
        when(users.getUsers()).thenReturn(new ArrayList<>());

        // Вызовем service, который вызывает doPost
        userController.service(request, response);

        // Проверим, что session установлены корректные атрибуты
        verify(session).setAttribute("current_username", USERNAME);
        verify(session).setAttribute("status", true);
        verify(session).setAttribute("text_user_notification", null);
        verify(response).sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Test
    void testFailedRegistration_UserAlreadyExists() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("Реєстрація");
        when(request.getParameter("username")).thenReturn(USERNAME);
        when(request.getParameter("password")).thenReturn(PASSWORD);
        when(users.searchUsername(USERNAME)).thenReturn(true); // Пользователь существует

        userController.service(request, response);

        // Проверим, что сообщение об ошибке и редирект верны
        verify(session).setAttribute("text_user_notification", "Користувач з таким username вже існує");
        verify(session).setAttribute("status", Boolean.FALSE);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testSuccessfulLogin() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("Ввійти");
        when(request.getParameter("username")).thenReturn(USERNAME);
        when(request.getParameter("password")).thenReturn(PASSWORD);
        when(users.searchUser(any(User.class))).thenReturn(true); // Пользователь найден

        userController.service(request, response);

        // Проверим, что session установлены корректные атрибуты
        verify(session).setAttribute("current_username", USERNAME);
        verify(session).setAttribute("status", true);
        verify(session).setAttribute("text_user_notification", null);
        verify(response).sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Test
    void testFailedLogin_IncorrectCredentials() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("Ввійти");
        when(request.getParameter("username")).thenReturn(USERNAME);
        when(request.getParameter("password")).thenReturn(PASSWORD);
        when(users.searchUser(any(User.class))).thenReturn(false); // Пользователь не найден

        userController.service(request, response);

        // Проверим, что сообщение об ошибке и редирект верны
        verify(session).setAttribute("text_user_notification", "Введи вірний логін та пароль");
        verify(session).setAttribute("status", Boolean.FALSE);
        verify(dispatcher).forward(request, response);
    }
}
