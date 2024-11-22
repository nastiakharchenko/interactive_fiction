package org.module_three.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.module_three.entity.QuestAlgorithm;

//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import java.io.IOException;

public class QuestServlet extends HttpServlet {

    /*
     * Чистим атрибуты сессии для квеста
     * */
    protected void clearAttribute(HttpSession currentSession){
        currentSession.setAttribute("congratulation", null);
        currentSession.setAttribute("active_quest", null);
        currentSession.setAttribute("numberQuestion", null);
        currentSession.setAttribute("question", null);
        currentSession.setAttribute("answer_var_1", null);
        currentSession.setAttribute("answer_var_2", null);
    }

    /*
     * Заполняем данными атрибуты сессии для квеста
     * */
    protected void addDataAttribute(HttpSession currentSession, QuestAlgorithm quest, int numberQuestion){
        currentSession.setAttribute("congratulation", false);
        currentSession.setAttribute("numberQuestion", numberQuestion);
        currentSession.setAttribute("question", quest.getQuestions().get(numberQuestion).getQuestion());
        currentSession.setAttribute("answer_var_1", quest.getQuestions().get(numberQuestion).getAnswer()[0]);
        currentSession.setAttribute("answer_var_2", quest.getQuestions().get(numberQuestion).getAnswer()[1]);
    }

    /*
     * Заполняем данные квеста в зависимости от этапа
     * */
    private int addDataQuest(HttpSession currentSession, QuestAlgorithm quest){
        int numberQuestion = (int)currentSession.getAttribute("numberQuestion");
        numberQuestion++;
        //Проверяем, есть ли еще вопросы:
        if(numberQuestion < quest.getQuestions().size()){
            addDataAttribute(currentSession, quest, numberQuestion);
        } else{
            //если нет, заменяем данными финальной страницы:
            currentSession.setAttribute("congratulation", true);
            currentSession.setAttribute("question", quest.getCongratulation());
            currentSession.setAttribute("image_question", quest.getCongratulationImage());
            //Шагаем последний раз, чтобы сработала проверка
            currentSession.setAttribute("numberQuestion", numberQuestion);
        }
        return numberQuestion;
    }

    /*
     * В зависимости от кнопки квеста выполнение нужных действий
     * */
    protected Boolean takeNecessaryAction(HttpSession currentSession, HttpServletRequest request, HttpServletResponse response, QuestAlgorithm quest, String action, String questStageTag) throws IOException {
        boolean actionReturn = false;
        switch (action) {
            case "Відповісти":
                String checkVariable = request.getParameter("answer");
                if (checkVariable != null) {
                    int numberQuestion = addDataQuest(currentSession, quest);
                    //Сравниваем выбранный вариант с правильным. Неправильный выбор - страница проигрыша
                    if (!checkVariable.equals(quest.getAnswers().get(numberQuestion - 1).getAnswer())) {
                        clearAttribute(currentSession);
                        currentSession.setAttribute(questStageTag, null);
                        response.sendRedirect(request.getContextPath() + "/loss.jsp");
                        actionReturn = true;
                    } else{
                        currentSession.setAttribute(questStageTag, (int)currentSession.getAttribute("numberQuestion"));
                    }
                }
                break;
            case "Назад":
                int numberQuestion = (int)currentSession.getAttribute("numberQuestion");
                numberQuestion = Math.max(numberQuestion - 1, 0);
                addDataAttribute(currentSession, quest, numberQuestion);
                currentSession.setAttribute(questStageTag, (int)currentSession.getAttribute("numberQuestion"));
                break;
            case "Вийти":
                currentSession.setAttribute(questStageTag, (int)currentSession.getAttribute("numberQuestion"));
                currentSession.setAttribute("congratulation", null);
                currentSession.setAttribute("numberQuestion", null);
                response.sendRedirect(request.getContextPath() + "/quests.jsp");
                actionReturn = true;
                break;
            case "Почати спочатку":
                clearAttribute(currentSession);
                currentSession.setAttribute(questStageTag, null);
                response.sendRedirect(request.getContextPath() + currentSession.getAttribute("pathCurrentQuest"));
                actionReturn = true;
                break;
            case "Повернутися до списку квестів":
                clearAttribute(currentSession);
                currentSession.setAttribute(questStageTag, null);
                response.sendRedirect(request.getContextPath() + "/quests.jsp");
                actionReturn = true;
                break;
        }
        return actionReturn;
    }
}
