package Utils;

import API.APIService;
import Authenticator.Authenticator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {
    public static String encase(String str) {
        return "\"" + str + "\"";
    }
    public static void loadConfig(String filename)
    {
        try {
            File file = new File(getResoursePath() +filename);
            Scanner in = new Scanner(file);
            while(in.hasNextLine())
            {
                String line = in.nextLine();
                processLine(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Config file not found!");
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Config file loading error!");
            System.exit(-2);
        }
    }

    public static String getResoursePath() {
        return System.getProperty("user.dir") + "/src/main/resources/";
    }

    private static void processLine(String line) {
        if (line.contains("endpoint=")) {
            APIService.SERVER_URL = line.replace("endpoint=", "").replace("\"", "");
        }
        if (line.contains("username=")) {
            Authenticator.setUsername(line.replace("username=", "").replace("\"", ""));
        }
        if (line.contains("password=")) {
            Authenticator.setPassword(line.replace("password=", "").replace("\"", ""));
        }
    }
}
