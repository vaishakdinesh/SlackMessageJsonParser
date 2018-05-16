package info6205.slack.jsonparser;

import info6205.slack.users.Professor;
import info6205.slack.users.TA;
import static info6205.slack.utility.Helper.USERS_MAP;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class SlackJsonUserParser {

    public void parseUsers(String fileName){
        JSONParser parser = new JSONParser();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile().replace("%20"," "));

        try {
            JSONArray users = (JSONArray) parser.parse(new FileReader(file));

            for(Object o : users){
                JSONObject jsonObject = (JSONObject) o;
                boolean isAdmin = (boolean) jsonObject.get("is_admin");
                if(isAdmin){
                    boolean isOwner = (boolean) jsonObject.get("is_owner");
                    String id = (String) jsonObject.get("id");
                    String realName = (String) jsonObject.get("real_name");

                    if(isAdmin && isOwner){
                        Professor prof = new Professor(id,realName,isOwner,isAdmin);
                        USERS_MAP.put(prof.getId(),prof);
                    }

                    if(isAdmin && !isOwner){
                        TA ta = new TA(id,realName,isOwner,isAdmin);
                        USERS_MAP.put(ta.getId(),ta);
                    }
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
