package com.resliv.test.telegrambot.controller;

import com.resliv.test.telegrambot.TourismTelegramBot;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {
    private final TourismTelegramBot tourismTelegramBot;

    public WebHookController(TourismTelegramBot tourismTelegramBot) {
        this.tourismTelegramBot = tourismTelegramBot;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return tourismTelegramBot.onWebhookUpdateReceived(update);
    }

}
