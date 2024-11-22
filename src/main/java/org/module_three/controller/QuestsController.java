package org.module_three.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.module_three.entity.quests.QuestCollection;

//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name="QuestsController", value="/quests")
public class QuestsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession currentSession = request.getSession();
        List<QuestCollection> quests = (List<QuestCollection>) currentSession.getAttribute("questList");
        String pathCheckQuest = quests
                .stream()
                .filter(quest -> quest.getName().equals(request.getParameter("current_quest")))
                .findFirst().get().getPath();
        currentSession.setAttribute("pathCurrentQuest", pathCheckQuest);
        response.sendRedirect(request.getContextPath() + pathCheckQuest);
    }
}
