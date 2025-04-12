package com.example.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class AppConfig {
    @Value("classpath:prompt.txt")
    private Resource resource;

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
//        return chatClientBuilder
//                .defaultSystem(resource) // System Message (LLM에 역할을 부여)
//                .build();

//        return chatClientBuilder.build();
        return chatClientBuilder.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory())).build();
    }
}
