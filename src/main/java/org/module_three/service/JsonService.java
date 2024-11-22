package org.module_three.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.module_three.entity.*;
import org.module_three.entity.quests.AngelAndDemon;
import org.module_three.entity.quests.QuestCollection;
import org.module_three.entity.quests.Ufo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonService {

    public static List<User> readFileJsonUser(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(fileName), new TypeReference<List<User>>() {});
    }

    public static void writeFileJsonUser(List<User> users, String fileName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(fileName), users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<QuestCollection> readFileJsonQuests(String fileName) {
        List<QuestCollection> questCollection = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(fileName)) {

            Object obj = jsonParser.parse(reader);

            JSONArray jsonArray = (JSONArray) obj;

            for (Object quest : jsonArray) {
                JSONObject jsonObject = (JSONObject) quest;

                questCollection.add(new QuestCollection(jsonObject.get("name").toString()
                        , jsonObject.get("background").toString()
                        , jsonObject.get("path").toString()
                        , jsonObject.get("description").toString()
                        , null
                        , null
                        , ""
                        , ""));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return questCollection;
    }

    public static AngelAndDemon readFileJsonQuestAngelAndDemon(String fileName) {
        AngelAndDemon angelAndDemon = null;

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(fileName)) {
            // Чтение JSON-файла и его преобразование в объект
            Object obj = jsonParser.parse(reader);

            JSONArray jsonArray = (JSONArray) obj;

            for (Object quest : jsonArray) {
                JSONObject jsonObject = (JSONObject) quest;

                String[] questions = jsonObject.get("questions")
                        .toString()
                        .replace("[","")
                        .replace("]","")
                        .replace("\\n", "<br>")
                        .split("\\*");
                questions = Arrays.stream(questions)
                        .map(str -> str.replace(Character.toString('*'), "").replace("\",\"", ""))
                        .toArray(String[]::new);
                questions[0] = questions[0].substring(1);
                questions[questions.length - 1] = questions[questions.length - 1].substring(0, questions[questions.length - 1].length() - 1);

                String[] answers = jsonObject.get("answers")
                        .toString()
                        .replace("[","")
                        .replace("]","")
                        .split("\\*");
                answers = Arrays.stream(answers)
                        .map(str -> str.replace(Character.toString('*'), "").replace("\",\"", ""))
                        .toArray(String[]::new);
                answers[0] = answers[0].substring(1);
                answers[answers.length - 1] = answers[answers.length - 1].substring(0, answers[answers.length - 1].length() - 1);
                answers = Arrays.stream(answers)
                        .map(str -> str.replace(Character.toString('*'), ""))
                        .toArray(String[]::new);

                String[] images = jsonObject.get("images")
                        .toString()
                        .replace("[","")
                        .replace("]","")
                        .replace("\"","")
                        .replace("\\","")
                        .split(",");

                String[] trueAnswers = jsonObject.get("right_answer")
                        .toString()
                        .replace("[","")
                        .replace("]","")
                        .replace("\"","")
                        .split(",");

                List<Question> questionList = new ArrayList<>();
                List<Answer> answerList = new ArrayList<>();
                for (int i = 0; i < questions.length; i++) {
                    questionList.add(new Question(i, questions[i], answers[i]));
                    answerList.add(new Answer(i, trueAnswers[i]));
                }

                angelAndDemon = new AngelAndDemon(jsonObject.get("name").toString()
                        , jsonObject.get("background").toString()
                        , jsonObject.get("path").toString()
                        , jsonObject.get("description").toString()
                        , questionList
                        , answerList
                        , jsonObject.get("congratulation").toString()
                        , jsonObject.get("congratulation_image").toString()
                        , images);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return angelAndDemon;
    }

    public static Ufo readFileJsonQuestUfo(String fileName){
        Ufo ufo = null;

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(fileName)) {
            // Чтение JSON-файла и его преобразование в объект
            Object obj = jsonParser.parse(reader);

            JSONArray jsonArray = (JSONArray) obj;

            for (Object quest : jsonArray) {
                JSONObject jsonObject = (JSONObject) quest;

                String[] questions = jsonObject.get("questions")
                        .toString()
                        .replace("[","")
                        .replace("]","")
                        .replace("\"","")
                        .split(",");

                String[] answers = jsonObject.get("answers")
                        .toString()
                        .replace("[","")
                        .replace("]","")
                        .replace("\"","")
                        .split(",");

                String[] trueAnswers = jsonObject.get("right_answer")
                        .toString()
                        .replace("[","")
                        .replace("]","")
                        .replace("\"","")
                        .split(",");

                String[] loss = jsonObject.get("loss")
                        .toString()
                        .replace("[","")
                        .replace("]","")
                        .replace("\"","")
                        .split(",");

                List<Question> questionList = new ArrayList<>();
                List<Answer> answerList = new ArrayList<>();
                for (int i = 0; i < questions.length; i++) {
                    questionList.add(new Question(i, questions[i], answers[i]));
                    answerList.add(new Answer(i, trueAnswers[i]));
                }

                ufo = new Ufo(jsonObject.get("name").toString()
                        , jsonObject.get("background").toString()
                        , jsonObject.get("path").toString()
                        , jsonObject.get("description").toString()
                        , questionList
                        , answerList
                        , jsonObject.get("congratulation").toString()
                        , jsonObject.get("congratulation_image").toString()
                        , loss
                        , jsonObject.get("image_loss").toString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return ufo;
    }
}
