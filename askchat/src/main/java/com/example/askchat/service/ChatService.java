package com.example.askchat.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getResponse(String message) {
        return chatModel.call(message);
    }

    public String getResponseOptions(String message) {
        return chatModel.call(
                new Prompt(
                        message,
                        OpenAiChatOptions.builder()
                                .model(OpenAiApi.ChatModel.GPT_4_O)
                                .temperature(0.4)
                                .build())
                )
                .getResult()
                .getOutput()
                .getText();
    }
}
