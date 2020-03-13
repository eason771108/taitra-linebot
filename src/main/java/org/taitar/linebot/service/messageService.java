package org.taitar.linebot.service;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@LineMessageHandler
public class messageService {

    private LineMessagingClient client;

    @Autowired
    public messageService(LineMessagingClient client) {
        this.client = client;
    }

    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
        TextMessageContent message = event.getMessage();
        String userId = event.getSource().getUserId();
        log.info("Receive msg from USER({}) : {}", userId, message.getText());
    }
}
