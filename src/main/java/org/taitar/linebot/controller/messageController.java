package org.taitar.linebot.controller;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
public class messageController {

    private LineMessagingClient client;

    @Value("${Server.image}")
    private String imgURL;

    @Value("${Server.pre}")
    private String preURL;

    @Value("${Server.sendto}")
    private String sendTo;

    @Autowired
    public messageController(LineMessagingClient client) {
        this.client = client;
    }

    @GetMapping(path = "/sendMessage")
    void pushTextMessage() throws ExecutionException, InterruptedException {
        TextMessage textMessage = new TextMessage("hello");
        PushMessage pushMessage = new PushMessage(
                "Uefe96d31b7ed1c0213d4a763fdeee2db",
                textMessage);

        BotApiResponse botApiResponse;
        botApiResponse = client.pushMessage(pushMessage).get();

        log.info("{} OK!", botApiResponse);
    }

    @GetMapping(path = "/sendImage")
    public void pushImgMessage() throws Exception {

        URI imgURI = URI.create( imgURL);
        URI preURI = URI.create( preURL);

        log.info("imgURI : {}", imgURI.toString());
        log.info("preURI : {}", preURI.toString());

        ImageMessage imageMessage = new ImageMessage(imgURI, preURI);
        PushMessage pushMessage = new PushMessage(
                sendTo,
                imageMessage);
        BotApiResponse botApiResponse;
        botApiResponse = client.pushMessage(pushMessage).get();

        log.info("{} OK!", botApiResponse);
    }
}
