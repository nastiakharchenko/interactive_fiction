package org.module_three.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.module_three.entity.quests.Ufo;
import org.module_three.service.JsonService;

//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.module_three.constant.FileNameConstants.FILE_QUEST_UFO;

@WebServlet(name="UfoServlet", value="/ufo")
public class UfoController extends QuestServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession currentSession = request.getSession();

        //Если пользователь не авторизовался, запретить доступ к квесту:
        if(currentSession.getAttribute("current_username") == null){
            response.sendRedirect(request.getContextPath() + "/authorization");
            return;
        }

        Boolean congratulations = (Boolean)currentSession.getAttribute("congratulation");

        int numberQuestion = 0;
        Integer stage = (Integer)currentSession.getAttribute("stage_ufo");
        if(stage != null){
            numberQuestion = stage;
            currentSession.setAttribute("numberQuestion", stage);
        }

        if(congratulations == null){
            //Первый запуск квеста:
            Ufo quest = JsonService.readFileJsonQuestUfo(FILE_QUEST_UFO);
            currentSession.setAttribute("active_quest", quest);
            addDataAttribute(currentSession, quest, numberQuestion);
        } else{
            Ufo quest = (Ufo)currentSession.getAttribute("active_quest");
            String action = request.getParameter("action");
            if(action != null){
                numberQuestion = (int) currentSession.getAttribute("numberQuestion");
                if(takeNecessaryAction(currentSession, request, response, quest, action, "stage_ufo")){
                    currentSession.setAttribute("image_loss", quest.getImageLoss());
                    currentSession.setAttribute("text_loss", quest.getLoss()[
                            numberQuestion - 1 < 0 ? numberQuestion :
                                numberQuestion == quest.getQuestions().size() ? numberQuestion - 1 : numberQuestion
                            ]);
                    return;
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/ufo.jsp");
    }
}
