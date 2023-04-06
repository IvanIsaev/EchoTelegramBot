package main.echo_telegram_bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class AppConfig
{
    @Bean
    public TelegramBotsApi createTelegramApi() throws TelegramApiException
    {
        return new TelegramBotsApi(DefaultBotSession.class);
    }
}