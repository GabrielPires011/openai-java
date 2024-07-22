package br.com.alura;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.util.Arrays;

public class TestaIntegracao {

    public static void main(String[] args) {
        var user = "Gere 5 produtos";
        var system = "Você e um gerador de produtos ficticios para um ecommerce e deve gerar apenas o nome dos produtos solicidados pelo o usuário";

        var chave = System.getenv("OPENAI_API_KEY");
        var service = new OpenAiService(chave);

        var completionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(Arrays.asList(new ChatMessage(ChatMessageRole.USER.value(), user),
                        new ChatMessage(ChatMessageRole.SYSTEM.value(), system)))
                .build();

        service
                .createChatCompletion(completionRequest)
                .getChoices()
                .forEach(c -> System.out.println(c.getMessage().getContent()));
    }
}
