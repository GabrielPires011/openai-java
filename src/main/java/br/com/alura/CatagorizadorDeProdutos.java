package br.com.alura;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.util.Arrays;
import java.util.Scanner;

public class CatagorizadorDeProdutos {

    public static void main(String[] args) {

        var scarnner = new Scanner(System.in);

        var system = "Você é um categorizador de produtos";

        System.out.print("Informe seu produto: ");
        var user = scarnner.nextLine();

        var chave = System.getenv("OPENAI_API_KEY");
        var service = new OpenAiService(chave);

        var completionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(Arrays.asList(new ChatMessage(ChatMessageRole.USER.value(), user),
                        new ChatMessage(ChatMessageRole.SYSTEM.value(), system)))
                .n(2)
                .build();

        service
                .createChatCompletion(completionRequest)
                .getChoices()
                .forEach(c -> {
                    System.out.println(c.getMessage().getContent());
                    System.out.println("---------------------------------------------");
                });
    }
}
