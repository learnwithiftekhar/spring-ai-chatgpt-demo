package com.learnwithiftekhar.spring_ai_demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    private final ChatClient chatClient;
    private final OpenAiImageModel openAiImageModel;

    public AIService(ChatClient.Builder builder, OpenAiImageModel imageModel) {
        chatClient = builder.build();
        this.openAiImageModel = imageModel;
    }

    public String chat(String query) {
        return chatClient
                .prompt()
                .user(query)
                .call()
                .content();
    }

    /**
     * Generates an image based on the provided instruction using an AI model.
     * Supported image sizes are:
     * - 1024x1024
     * - 1024x1536
     * - 1536x1024
     * Supported image quality values are 'hd' or 'standard'.
     *
     * @param instruction a textual description or instruction for generating the image
     * @return a URL or identifier for the generated image
     */
    public String generateImage(String instruction) {

        OpenAiImageOptions imageOptions = OpenAiImageOptions.builder()
                .height(1024)
                .width(1024)
                .quality("hd")
                .N(1)
                .build();

        ImageResponse response = openAiImageModel.call(
                new ImagePrompt(
                        instruction,
                        imageOptions
                )
        );

        return response.getResult().getOutput().getUrl();
    }
}
