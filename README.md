# Ficha de Cadastro - Multinegociações ✅

Este é um projeto Java Spring Boot para automatizar o preenchimento de fichas cadastrais via WhatsApp Business API usando a Z-API. Ele coleta os dados dos clientes etapa por etapa, preenche um PDF e envia o arquivo automaticamente para o número vinculado.

---

## 🚀 Funcionalidades

- Recebe mensagens via webhook usando Z-API
- Coleta dados do cliente de forma conversacional
- Gera automaticamente um PDF preenchido
- Envia o PDF para o WhatsApp configurado

---

## ☁️ Deploy na Render

### ✅ Build command:
```
mvn clean install
```

### ▶️ Start command:
```
java -jar target/cadastro-api-1.0.0.jar
```

---

## 🔗 Configuração na Z-API

Acesse o painel da Z-API e em **"Ao receber"** configure o webhook com a URL da sua Render:

```
https://seuapp.onrender.com/webhook
```

---

## 👤 Desenvolvido por:
Heverton Júnior  
📱 WhatsApp vinculado: +55 47 98426-0402  
🌐 Projeto gerado e automatizado com suporte do ChatGPT

