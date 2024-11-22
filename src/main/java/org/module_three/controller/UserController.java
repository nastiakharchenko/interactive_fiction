package org.module_three.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.module_three.entity.User;
import org.module_three.entity.UserCollection;
import org.module_three.service.JsonService;

//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.module_three.constant.FileNameConstants.FILE_USERS;

@WebServlet(name="UserServlet", value="/authorization")
public class UserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession currentSession = request.getSession();
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserCollection users = new UserCollection();    //получаем всех пользователей

        String action = request.getParameter("action");
        if(action != null) {
            boolean success = false;
            switch(action){
                case "Реєстрація":
                    if(!users.searchUsername(username)){     //проверяем, что такого username нет
                        users.getUsers().add(new User(username, password));
                        JsonService.writeFileJsonUser(users.getUsers(), FILE_USERS);
                        success = true;
                    }
                    currentSession.setAttribute("text_user_notification", "Користувач з таким username вже існує");
                    break;
                case "Ввійти":
                    if(users.searchUser(new User(username, password))){     //проверяем, что такой пользователь существует
                        success = true;
                    }
                    currentSession.setAttribute("text_user_notification", "Введи вірний логін та пароль");
                    break;
            }
            if(success){
                currentSession.setAttribute("current_username", username);
                currentSession.setAttribute("status", Boolean.TRUE);
                currentSession.setAttribute("text_user_notification", null);
                response.sendRedirect(request.getContextPath() + "/quests.jsp");
                return;
            }
        }
        currentSession.setAttribute("status", Boolean.FALSE);
        request.getRequestDispatcher(request.getContextPath() + "/authorization.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
