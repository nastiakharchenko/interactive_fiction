package org.module_three.service;

import lombok.Getter;
import org.module_three.entity.quests.QuestCollection;

import java.io.IOException;
import java.util.List;

import static org.module_three.constant.FileNameConstants.FILE_QUESTS;

@Getter
public class FirstProgramDataLoader {
    private List<QuestCollection> quests;

    public FirstProgramDataLoader() {
        try{
            quests = loadQuests();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private List<QuestCollection> loadQuests() throws IOException {
        return JsonService.readFileJsonQuests(FILE_QUESTS);
    }
}
