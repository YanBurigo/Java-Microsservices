package io.github.yanburigo.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.yanburigo.vo.request.ChatGptRequest;
import io.github.yanburigo.vo.response.ChatGptResponse;

@Service
public class ChatGPTService {

    private Logger logger = Logger.getLogger
            (ChatGPTService.class.getName());
    
    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.url}")
    private String url;
    
    @Autowired
    private RestTemplate template;
    
    public String chat(String prompt) {
        
        logger.info("Starting Prompt");
        
        ChatGptRequest request = new ChatGptRequest(model, prompt);
        
        logger.info("Processing Prompt");
        ChatGptResponse response = 
                template.postForObject(url, request, ChatGptResponse.class);

        return response.getChoices().get(0).getMessage().getContent();
    }
}
