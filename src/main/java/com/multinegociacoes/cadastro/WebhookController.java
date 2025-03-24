package com.multinegociacoes.cadastro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final Map<String, String> dados = new HashMap<>();
    private int etapa = 0;

    @PostMapping
    public ResponseEntity<?> receberMensagem(@RequestBody Map<String, Object> payload) {
        try {
            Map<String, Object> message = (Map<String, Object>) payload.get("message");
            String texto = message.get("text").toString();

            switch (etapa) {
                case 0:
                    dados.put("NOME_COMPLETO", texto);
                    enviarMensagem("Qual seu CPF?");
                    break;
                case 1:
                    dados.put("CPF", texto);
                    enviarMensagem("Qual seu RG?");
                    break;
                case 2:
                    dados.put("RG", texto);
                    enviarMensagem("Qual sua data de nascimento?");
                    break;
                case 3:
                    dados.put("DATA_NASC", texto);
                    enviarMensagem("Qual seu estado civil?");
                    break;
                case 4:
                    dados.put("ESTADO_CIVIL", texto);
                    String caminho = PdfPreenchimento.preencherPDF(dados);
                    enviarArquivoPDF(caminho);
                    enviarMensagem("Cadastro finalizado. Seu PDF foi gerado e enviado.");
                    break;
            }
            etapa++;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    private void enviarMensagem(String texto) {
        RestTemplate rest = new RestTemplate();
        String url = "https://api.z-api.io/instances/instance5477C8B57ADBD72F75CBC8EA/token/5477C8B57ADBD72F75CBC8EA/send-text";

        Map<String, String> body = new HashMap<>();
        body.put("phone", "5547984260402");
        body.put("message", texto);

        rest.postForObject(url, body, String.class);
    }

    private void enviarArquivoPDF(String caminhoArquivo) throws Exception {
        RestTemplate rest = new RestTemplate();
        String url = "https://api.z-api.io/instances/instance5477C8B57ADBD72F75CBC8EA/token/5477C8B57ADBD72F75CBC8EA/send-file";

        Map<String, String> body = new HashMap<>();
        body.put("phone", "5547984260402");
        body.put("message", "Segue seu PDF gerado:");
        body.put("filename", "FichaCadastro.pdf");

        byte[] bytes = Files.readAllBytes(new File(caminhoArquivo).toPath());
        String base64 = java.util.Base64.getEncoder().encodeToString(bytes);
        body.put("base64", base64);

        rest.postForObject(url, body, String.class);
    }
}
