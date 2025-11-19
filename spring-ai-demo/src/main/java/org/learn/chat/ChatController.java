package org.learn.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {
    private ChatClient chatClient;

    public ChatController(OllamaChatModel builder){
        this.chatClient = ChatClient.create(builder);
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String input){
        return chatClient.prompt()
                .user(input)
                .call()
                .content();
    }
}
