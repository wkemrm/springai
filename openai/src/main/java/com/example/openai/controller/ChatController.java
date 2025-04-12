package com.example.openai.controller;

import com.example.openai.entity.Answer;
import com.example.openai.entity.Movie;
import com.example.openai.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message) {
        return chatService.chat(message);
    }

    @GetMapping("/chatmessage")
    public String chatMessage(@RequestParam("message") String message) {
        return chatService.chatMessage(message);
    }

    @GetMapping("/chatplace")
    public String chatPlace(String subject, String tone, String message) {
        return chatService.chatPlace(subject, tone, message);
    }

    @GetMapping("/chatjson")
    public ChatResponse chatJson(String message) {
        return chatService.chatJson(message);
    }

    @GetMapping("/chatobject")
    public Answer chatObject(String message) {
        return chatService.chatObject(message);
    }

    @GetMapping("/recipe")
    public Answer recipe(String foodName, String question) {
        return chatService.recipe(foodName, question);
    }

    @GetMapping("/chatlist")
    public List<String> chatList(String message) {
        return chatService.chatList(message);
    }

    @GetMapping("/chatmap")
    public Map<String, String> chatMap(String message) {
        return chatService.chatmap(message);
    }

    @GetMapping("/chatmovie")
    public List<Movie> chatMovie(String directorName) {
        return chatService.chatMovie(directorName);
    }
}
