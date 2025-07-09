package com.learnwithiftekhar.spring_ai_demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    private final ChatClient chatClient;

    public AIService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    public String chat(String query) {
        return chatClient
                .prompt()
                .user(query)
                .call()
                .content();
    }
}
