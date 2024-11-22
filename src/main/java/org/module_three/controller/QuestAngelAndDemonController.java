package org.module_three.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.module_three.entity.quests.AngelAndDemon;
import org.module_three.service.JsonService;

//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.module_three.constant.FileNameConstants.FILE_QUEST_ANGEL_AND_DEMON;

@WebServlet(name="QuestAngelAndDemonServlet", value="/angel_and_demon")
public class QuestAngelAndDemonController extends QuestServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession currentSession = request.getSession();

        //Если пользователь не авторизовался, запретить доступ к квесту:
        if(currentSession.getAttribute("current_username") == null){
            response.sendRedirect(request.getContextPath() + "/authorization");
            return;
        }

        Boolean congratulations = (Boolean)currentSession.getAttribute("congratulation");

        //Проверяем, что квест активен:
        int numberQuestion = 0;
        Integer stage = (Integer)currentSession.getAttribute("stage_angel_and_demon");
        if(stage != null){
            numberQuestion = stage;
            currentSession.setAttribute("numberQuestion", stage);
        }

        if(congratulations == null){
            //Первый запуск квеста:
            AngelAndDemon quest = JsonService.readFileJsonQuestAngelAndDemon(FILE_QUEST_ANGEL_AND_DEMON);
            currentSession.setAttribute("active_quest", quest);
            addDataAttribute(currentSession, quest, numberQuestion);
            addDataQuestImage(currentSession, quest);
        } else{
            AngelAndDemon quest = (AngelAndDemon)currentSession.getAttribute("active_quest");
            String action = request.getParameter("action");
            if(action != null){
                if(takeNecessaryAction(currentSession, request, response, quest, action, "stage_angel_and_demon")){
                    currentSession.setAttribute("image_question", null);
                    currentSession.setAttribute("image_loss", "image/angel_and_demon/loss.png");
                    currentSession.setAttribute("text_loss", "На жаль ти програв...");
                    return;
                }
            }
            addDataQuestImage(currentSession, quest);
        }

        response.sendRedirect(request.getContextPath() + "/angel_and_demon.jsp");
    }

    /*
    * Заполняем атрибут картинки в зависимости от этапа
    * */
    private void addDataQuestImage(HttpSession currentSession, AngelAndDemon quest){
        int numberQuestion = (int)currentSession.getAttribute("numberQuestion");
        //Проверяем, есть ли еще вопросы:
        if(numberQuestion < quest.getQuestions().size()){
            currentSession.setAttribute("image_question", quest.getImagesQuestion()[numberQuestion]);
        }
    }
}
