package utils;

import jdk.nashorn.internal.parser.JSONParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import data.LoginData;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonReaderHelper  {
    public static LoginData[] getLoginData() throws FileNotFoundException{
        JsonElement root = new JsonParser().parse(new FileReader(System.getProperty("user.dir") + "/src/main/resources/data/loginData.json"));
        Gson gson = new Gson();
        LoginData[] loginData = gson.fromJson(root, LoginData[].class);
        return loginData;
    }
}
