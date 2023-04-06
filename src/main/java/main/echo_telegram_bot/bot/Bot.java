package main.echo_telegram_bot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Logger;

@Component
public class Bot extends TelegramLongPollingBot
{
    Logger logger;

    private String botName;

    public Bot(@Value("${bot.token}") String botToken,
               @Value("${bot.name}") String botName,
               TelegramBotsApi telegramBotsApi) throws TelegramApiException
    {
        super(botToken);

        this.botName = botName;

        logger = Logger.getLogger(Bot.class.getName());
        telegramBotsApi.registerBot(this);
    }

    @Override
    public String getBotUsername()
    {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        final String text = update.getMessage().getText();
        if(text == null)
            return;

        logger.info(text);
        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId());

        if(update.getMessage().getText().equalsIgnoreCase("Ты пидор"))
        {
            msg.setText("Сам ты пидор");
            msg.setReplyToMessageId(update.getMessage().getMessageId());
        }
        else
            msg.setText(update.getMessage().getText());

        try
        {
            execute(msg);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }
}
