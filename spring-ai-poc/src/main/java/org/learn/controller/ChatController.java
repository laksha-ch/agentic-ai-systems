package org.learn.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

    @Autowired
    @Qualifier("ollamaChatClient")
    private ChatClient chatClient;

    @PostMapping("/chat")
    public String chat(@RequestBody String input){
        return chatClient.prompt()
                .advisors(new SimpleLoggerAdvisor())
                .user(u -> u
                        .text("Tell me the names of 5 movies whose soundtrack was composed by {composer}")
                        .param("composer", input))
                .call()
                .content();
    }
}
