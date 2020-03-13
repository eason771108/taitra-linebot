package org.taitar.linebot.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.taitar.linebot.controller.messageController;

@Slf4j
@Component
public class ScheduleTasks {

    private messageController controller;

    @Autowired
    public ScheduleTasks(messageController controller) {
        this.controller = controller;
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void reportEveryMorning() throws Exception {
        controller.pushImgMessage();
    }
}
