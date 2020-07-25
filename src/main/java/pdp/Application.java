package pdp;

import com.google.gson.Gson;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import pdp.bot.Cource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Application extends TelegramLongPollingBot {
    public static String exchange = "";
    public static boolean isSum = true;
    public static String letter = "";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new Application());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        System.out.println(message.getText());
        String text = message.getText();
        String s = text.equals("O'zbek") ? "Summani kiriting" : "Введите Сумму";
        switch (text) {
            case "Доллар-Сум":
                isSum = text.startsWith("Сум");
                exchange = "USD";
                letter = "сум";
                break;
            case "Сум-Доллар":
                isSum = text.startsWith("Сум");
                exchange = "USD";
                letter = "доллар";
                break;
            case "Евро-Сум":
                isSum = text.startsWith("Сум");
                exchange = "EUR";
                letter = "сум";
                break;
            case "Сум-Евро":
                isSum = text.startsWith("Сум");
                exchange = "EUR";
                letter = "евро";
                break;
            case "Рубл-Сум":
                isSum = text.startsWith("Сум");
                exchange = "RUB";
                letter = "сум";
                break;
            case "Сум-Рубл":
                isSum = text.startsWith("Сум");
                exchange = "RUB";
                letter = "рубл";
                break;
            case "Dollar-So'm":
                isSum = text.startsWith("So'm");
                exchange = "USD";
                letter = "so'm";
                break;
            case "So'm-Dollar":
                isSum = text.startsWith("So'm");
                exchange = "USD";
                letter = "dollor";
                break;
            case "Euro-So'm":
                isSum = text.startsWith("So'm");
                exchange = "EUR";
                letter = "so'm";
                break;
            case "So'm-Euro":
                isSum = text.startsWith("So'm");
                exchange = "EUR";
                letter = "yevro";
                break;
            case "Rubl-So'm":
                isSum = text.startsWith("So'm");
                exchange = "RUB";
                letter = "so'm";
                break;
            case "So'm-Rubl":
                isSum = text.startsWith("So'm");
                exchange = "RUB";
                letter = "rubl";
                break;
            case "O'zbek":
                s="O'zbek";
                break;
            case "Rus":
                s="Rus";
                break;
            case "/start":
                s="Tilni tanlang";
                break;
            case "<< Orqaga":
                s="Tilni tanlang";
                break;
            case "<< Назад":
                s="Tilni tanlang";
                break;
            default:
                try {
                    s=convertor(Double.parseDouble(text));
                } catch (NumberFormatException e){
                    s="Please enter number!";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        sendMessage(message, s);

    }

    public String getBotUsername() {
        return "valyutakurslari_bot";
    }

    public String getBotToken() {
        return "1329619646:AAFafL19Z4PJ1eu3uufNgqKh49NLF-mMpQ8";
    }




    public void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        setButton(sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setButton(SendMessage sendMessage) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(markup);
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow first = new KeyboardRow();
        if (sendMessage.getText().contains("Tilni")){
            first.add(new KeyboardButton("O'zbek"));
            first.add(new KeyboardButton("Rus"));
            rows.add(first);
        }else
            if (sendMessage.getText().contains("O'zbek")){
        first.add(new KeyboardButton("Dollar-So'm"));
        first.add(new KeyboardButton("So'm-Dollar"));
        KeyboardRow second = new KeyboardRow();
        second.add(new KeyboardButton("Euro-So'm"));
        second.add(new KeyboardButton("So'm-Euro"));
        KeyboardRow third = new KeyboardRow();
        third.add(new KeyboardButton("Rubl-So'm"));
        third.add(new KeyboardButton("So'm-Rubl"));
                KeyboardRow tort = new KeyboardRow();
                tort.add(new KeyboardButton("<< Orqaga"));
                rows.add(first);
                rows.add(second);
                rows.add(third);
                rows.add(tort);
            }else
            if (sendMessage.getText().contains("Rus")){
                first.add(new KeyboardButton("Доллар-Сум"));
                first.add(new KeyboardButton("Сум-Доллар"));
                KeyboardRow second = new KeyboardRow();
                second.add(new KeyboardButton("Евро-Сум"));
                second.add(new KeyboardButton("Сум-Евро"));
                KeyboardRow third = new KeyboardRow();
                third.add(new KeyboardButton("Рубл-Сум"));
                third.add(new KeyboardButton("Сум-Рубл"));
                KeyboardRow tort = new KeyboardRow();
                tort.add(new KeyboardButton("<< Назад"));
                rows.add(first);
                rows.add(second);
                rows.add(third);
                rows.add(tort);
            }

        markup.setKeyboard(rows);
    }

    public String convertor(Double summ) throws Exception {
        URL url = new URL("http://cbu.uz/uzc/arkhiv-kursov-valyut/json/");
        URLConnection connection = url.openConnection();
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        Cource[] cources = gson.fromJson(reader, Cource[].class);
        double rate = 0;
        for (Cource cource : cources) {
            if (cource.getCcy().equals(exchange)) {
                rate = Double.parseDouble(cource.getRate());
            }
        }
        NumberFormat formatter = new DecimalFormat("###,###.##");
        return isSum ? String.valueOf(formatter.format(summ / rate))+" "+letter : String.valueOf(formatter.format(summ * rate))+" "+letter;
    }

}
