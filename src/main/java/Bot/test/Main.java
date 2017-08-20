package Bot.test;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.management.StringValueExp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends TelegramLongPollingBot {


    public Main() throws SQLException {
    }


    public static void main(String[] args) throws SQLException {
        String test2;



        //api telegram
        ApiContextInitializer.init();

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();


        try {
            telegramBotsApi.registerBot(new Main());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public static String OutBD(String st2){

        System.out.println(st2);
        return st2;
    }

    public int returnBB(int v){
        return v;

    }




    /// Telegram APi
   public void sendMsg(Message massage, String ghe) {





        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);


        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);




        /// Instal keybord///////////

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("Комманда 1");
        keyboardFirstRow.add("Комманда 2");

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add("Комманда 3");
        keyboardSecondRow.add("Комманда 4");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(massage.getChatId().toString());





        //Вывоит сообщение от отправителя
        String s  = sendMessage.setReplyToMessageId(massage.getMessageId()).toString();

        sendMessage.setText(s);
        sendMessage.setText(ghe);
        try{
            sendMessage(sendMessage);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message massage = update.getMessage();

      //
        if(massage!= null && massage.hasText() ){

            List<DBResult> results = new ArrayList<DBResult>();


                DBConnector dbConnectorn = new DBConnector();

           

            String query = "select custumer_name from test_orders where number = " + Integer.parseInt(massage.getText());


                try {
                    Statement statement = dbConnectorn.getConnection().createStatement();

                    ResultSet resultSet = statement.executeQuery(query);



                   if (resultSet.next()) {
                        String order = resultSet.getString(1);
                        DBResult dbResult = new DBResult(order);
                        results.add(dbResult);

                      sendMsg(massage,order);
                    }else{
                       sendMsg(massage,"not found");
                   }


                    resultSet.close();
                } catch (SQLException e1) {
                    sendMsg(massage,"Not found");
                }



            }
            if(massage.getText().equals("/start")){
                sendMsg(massage,"Im loh");

            }


        }



    @Override
    public String getBotUsername() {
        return "GilTestSQLbot";
    }

    @Override
    public String getBotToken() {
        return "408200547:AAHfnftdGrtFP3gQ1AcDbItAPd2FrDMCQwA";
    }

    @Override
    public void onClosing() {

    }
}

