package info6205.slack;

import info6205.slack.jsonparser.SlackJsonMessageParser;
import info6205.slack.jsonparser.SlackJsonUserParser;
import info6205.slack.utility.Helper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    public String getFile(String fileName) {
        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile().replace("%20"," "));
        System.out.println(file.exists());

        try (Scanner scanner = new Scanner(file.getCanonicalFile())) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public void listFilesForFolder(String folderName) {

        //Get files from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File folder = new File(classLoader.getResource(folderName).getFile().replace("%20"," "));
        System.out.println(folder.exists());
        SlackJsonMessageParser messageParser = new SlackJsonMessageParser();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry.getName());
            } else {
                System.out.println(fileEntry.getName());
                messageParser.parseMessages(fileEntry);

            }
        }
    }

    public static void main (String[] args){
        Main main = new Main();
        SlackJsonUserParser sjup = new SlackJsonUserParser();
        sjup.parseUsers(Helper.USERS);

        main.listFilesForFolder(Helper.ASSIGNMENTS_FOLDER);
        main.listFilesForFolder(Helper.GENERAL_FOLDER);

    }
}


