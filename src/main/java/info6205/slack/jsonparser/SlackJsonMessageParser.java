package info6205.slack.jsonparser;

import info6205.slack.users.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static info6205.slack.utility.Helper.USERS_MAP;

public class SlackJsonMessageParser{

    public void parseMessages(File file){
        JSONParser parser = new JSONParser();

        try {
            JSONArray messages = (JSONArray) parser.parse(new FileReader(file));

            for(Object o : messages){
                JSONObject jsonObject = (JSONObject) o;
                String id = (String) jsonObject.get("user");
                if(USERS_MAP.containsKey(id)){
                    User user = USERS_MAP.get(id);
                    String text = (String) jsonObject.get("text");
                    System.out.println("user = "+((User) user).getRealName()+" message = "+text);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
