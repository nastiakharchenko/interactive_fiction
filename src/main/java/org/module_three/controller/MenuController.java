package org.module_three.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.module_three.service.FirstProgramDataLoader;

//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="MenuServlet", value="/menu")
public class MenuController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession currentSession = request.getSession();
        Boolean startGame = (Boolean) currentSession.getAttribute("startGame");

        if(startGame == null) {
            FirstProgramDataLoader questsData = new FirstProgramDataLoader();
            currentSession.setAttribute("questList", questsData.getQuests());
            currentSession.setAttribute("startGame", true);
        }

        switch(request.getParameter("action")){
            case "Головна":
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                break;
            case "Квести":
                response.sendRedirect(request.getContextPath() + "/quests.jsp");
                break;
            case "Створити квест":
                response.sendRedirect(request.getContextPath() + "/create_quest.jsp");
                break;
            case "Відгуки":
                response.sendRedirect(request.getContextPath() + "/reviews.jsp");
                break;
            case "Про нас":
                response.sendRedirect(request.getContextPath() + "/about_company.jsp");
                break;
            case "Профіль":
                response.sendRedirect(request.getContextPath() + "/authorization.jsp");
                break;
        }
    }

}
