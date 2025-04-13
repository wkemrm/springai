package com.example.imagegen.service;

import com.example.imagegen.entity.ImageRequestDto;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final OpenAiImageModel openAiImageModel;

    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse getIMageGen(ImageRequestDto request) {
        return openAiImageModel.call(
                new ImagePrompt(
                        request.getMessage(),
                        OpenAiImageOptions.builder()
                                .model(request.getModel())
                                .quality("hd")
                                .withN(request.getN())
                                .height(1024)
                                .width(1024)
                                .build()
                )
        );
    }
}
