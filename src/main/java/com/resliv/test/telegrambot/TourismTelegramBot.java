package com.resliv.test.telegrambot;

import com.resliv.test.telegrambot.service.SightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TourismTelegramBot extends TelegramWebhookBot {

    private static final String START_COMMAND = "/start";
    private static final String GREETING = "Hello! I am a bot for sights in cities around the world.\n" +
            "To start communication with me please enter the name of the city.";

    @Autowired
    private SightService sightService;

    private String token;
    private String username;
    private String webHookPath;

    public TourismTelegramBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());

        if (update.getMessage().getText().equals(START_COMMAND)) {
            message.setText(GREETING);
        } else {
            message.enableMarkdown(true);
            message.setText(sightService.findSight(update.getMessage().getText()));
        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
