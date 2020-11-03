package com.resliv.test.telegrambot.config;

import com.resliv.test.telegrambot.TourismTelegramBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class TelegramBotConfig {

    private String token;
    private String username;
    private String webHookPath;

    @Bean
    public TourismTelegramBot TelegramBot() {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);

        TourismTelegramBot tourismTelegramBot = new TourismTelegramBot(options);
        tourismTelegramBot.setUsername(username);
        tourismTelegramBot.setToken(token);
        tourismTelegramBot.setWebHookPath(webHookPath);

        return tourismTelegramBot;
    }
}
