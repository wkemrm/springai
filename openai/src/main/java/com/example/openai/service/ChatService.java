package com.example.openai.service;

import com.example.openai.entity.Answer;
import com.example.openai.entity.Movie;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String message) {
        return chatClient.prompt() // 프롬프트 생성
                .user(message) // 사용자 메세지
                .call() // 호출
                .content(); // 요청 정보를 받는 부분
    }

    public String chatMessage(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
    }

    public String chatPlace(String subject, String tone, String message) {
        return chatClient.prompt()
                .user(message)
                .system(
                        sp -> sp
                                .param("subject", subject)
                                .param("tone", tone)
                )
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
    }

    public ChatResponse chatJson(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();
    }

    public Answer chatObject(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(Answer.class);
    }

    private final String recipeTemplate = """
            Answer for {foodName} for {question}?
            """;
    public Answer recipe(String foodName, String question) {
        return chatClient.prompt()
                .user(userSpec -> userSpec.text(recipeTemplate)
                        .param("foodName", foodName)
                        .param("question", question))
                .call()
                .entity(Answer.class);
    }

    public List<String> chatList(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(new ListOutputConverter(new DefaultConversionService()));
    }

    public Map<String, String> chatmap(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(new ParameterizedTypeReference<Map<String, String>>() {


                });
    }

    public List<Movie> chatMovie(String directorName) {
        String template = """
                "Generate a list of movies directed by {directorName}. If the director is unknown, return null.
                 한국 영화는 한글로 표기해줘.
                 Each movie should include a title and release year. {format}
                """;
        List<Movie> movieList = chatClient.prompt()
                .user(userSpec -> userSpec.text(template)
                        .param("directorName", directorName)
                        .param("format", "json"))
                .call()
                .entity(new ParameterizedTypeReference<List<Movie>>() {
                });
        return movieList;
    }

    public String getResponse(String message){
        return chatClient.prompt().user(message).call().content();
    }

    public void startChat(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your message: ");
        while(true){
            String message=scanner.nextLine();
            if(message.equals("exit")){
                System.out.println("Exiting chat...");
                break;
            }
            String response=getResponse(message);
            System.out.println("Bot: "+ response);
        }
        scanner.close();
    }
}
